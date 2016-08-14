package org.apache.sqoop.core

import java.io._
import java.net.{MalformedURLException, URL, URLClassLoader}
import java.util.Properties

import org.apache.commons.lang3.StringUtils
import org.apache.logging.log4j.core.LoggerContext
import org.apache.logging.log4j.core.config.ConfigurationSource
import org.apache.logging.log4j.core.config.builder.api.Component
import org.apache.logging.log4j.core.config.properties.PropertiesConfiguration
import org.apache.logging.log4j.{LogManager, Logger}
import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}
import org.apache.sqoop.common.SqoopException
import org.apache.sqoop.core.ConfigurationConstants._
import org.apache.sqoop.error.code.CoreError

import scala.beans.BeanProperty
import scala.collection.mutable.ListBuffer

//import scala.collection.mutable._

/**
  * Configuration manager that loads Sqoop configuration.
  */
object SqoopConfiguration {
	/**
	  * Logger object.
	  */
	val LOG: Logger = LogManager.getLogger(classOf[SqoopConfiguration])

	@BeanProperty val instance: SqoopConfiguration = new SqoopConfiguration

	class CoreConfigurationListener(var listener: Reconfigurable) extends ConfigurationListener {
		def configurationChanged() {
			listener.configurationChanged
		}
	}

}

@InterfaceAudience.Public
@InterfaceStability.Unstable
class SqoopConfiguration private() extends Reconfigurable {

	import SqoopConfiguration._

	def configureLogging(): Unit = {
		val props: Properties = new Properties

		config.filter((kv: (String, String)) => kv._1.startsWith(PREFIX_LOG_CONFIG))
			.foreach((kv: (String, String)) => props.put(kv._1, kv._2))

		if (props.isEmpty) {
			LOG.info("Skipping log4j configuration as it's not configured in sqoop.properties file.")
			return
		}
		val baos: ByteArrayOutputStream = new ByteArrayOutputStream
		props.store(baos, "")
		LoggerContext.getContext.updateLoggers(new PropertiesConfiguration(new ConfigurationSource(new ByteArrayInputStream(baos.toByteArray)), new Component("Properties")))
	}

	/**
	  * Method to notify each reconfigurable components
	  */
	override def configurationChanged() {
		oldConfig = config
		config = provider.getConfiguration
		configureLogging()
	}

	private var configDir: File = null
	private var initialized: Boolean = false
	private var provider: ConfigurationProvider = null
	def getProvider: ConfigurationProvider = provider

	private var config: Map[String, String] = null
	def getContext:Map[String, String] = config
	private var oldConfig: Map[String, String] = null


	def initialize(): Unit = synchronized({
		if (initialized) {
			LOG.warn("Attempt to reinitialize the system, ignoring")
			return
		}

		val configDirPath: String = System.getProperty(SYSPROP_CONFIG_DIR)
		if (StringUtils.isBlank(configDirPath))
			throw new SqoopException(CoreError.CORE_0001, "Environment variable " + SYSPROP_CONFIG_DIR + " is not set.")

		configDir = new File(configDirPath)
		if (!configDir.exists || !configDir.isDirectory) throw new SqoopException(CoreError.CORE_0001, configDirPath)

		var bootstrapConfigFilePath: String = null
		try {
			val configDirCanonicalPath: String = configDir.getCanonicalPath
			bootstrapConfigFilePath = configDirCanonicalPath + "/" + ConfigurationConstants.FILENAME_BOOTCFG_FILE
		} catch {
			case ex: IOException => {
				throw new SqoopException(CoreError.CORE_0001, configDirPath, ex)
			}
		}

		val bootstrapConfig: File = new File(bootstrapConfigFilePath)
		if (!bootstrapConfig.exists || !bootstrapConfig.isFile || !bootstrapConfig.canRead) throw new SqoopException(CoreError.CORE_0002, bootstrapConfigFilePath)

		val bootstrapProperties: Properties = new Properties
		try {
			val bootstrapPropStream: InputStream = new FileInputStream(bootstrapConfig)
			try {
				bootstrapProperties.load(bootstrapPropStream)
			} catch {
				case ex: IOException => {
					throw new SqoopException(CoreError.CORE_0002, bootstrapConfigFilePath, ex)
				}
			} finally {
				if (bootstrapPropStream != null) bootstrapPropStream.close()
			}
		}

		val configProviderClassName: String = bootstrapProperties.getProperty(ConfigurationConstants.BOOTCFG_CONFIG_PROVIDER)
		if (StringUtils.isBlank(configProviderClassName)) throw new SqoopException(CoreError.CORE_0003, ConfigurationConstants.BOOTCFG_CONFIG_PROVIDER)

		var configProviderClass: Class[_] = null
		try
			configProviderClass = Class.forName(configProviderClassName)
		catch {
			case cnfe: ClassNotFoundException => {
				LOG.warn("Exception while trying to load configuration provider", cnfe)
			}
		}

		if (configProviderClass == null) {
			val ctxLoader: ClassLoader = Thread.currentThread.getContextClassLoader
			if (ctxLoader != null) try
				configProviderClass = ctxLoader.loadClass(configProviderClassName)
			catch {
				case cnfe: ClassNotFoundException => {
					LOG.warn("Exception while trying to load configuration provider: " + configProviderClassName, cnfe)
				}
			}
		}

		if (configProviderClass == null) throw new SqoopException(CoreError.CORE_0004, configProviderClassName)

		try
			provider = configProviderClass.newInstance.asInstanceOf[ConfigurationProvider]
		catch {
			case ex: Exception => {
				throw new SqoopException(CoreError.CORE_0005, configProviderClassName, ex)
			}
		}

		// Initialize the configuration provider
		provider.initialize(configDir, bootstrapProperties)
		configurationChanged
		provider.registerListener(new SqoopConfiguration.CoreConfigurationListener(SqoopConfiguration.getInstance))

		initialized = true
		configureClassLoader
	})

	private def configureClassLoader: Unit = synchronized {
		LOG.info("Adding jars to current classloader from property: " + ConfigurationConstants.CLASSPATH)
		val urls: List[URL] = getJarsForProperty(ConfigurationConstants.CLASSPATH)
		// Chain the current thread classloader so that
		// configured classpath adds to existing classloader.
		// Existing classpath is not changed.
		val currentThreadClassLoader: ClassLoader = Thread.currentThread.getContextClassLoader
		if (currentThreadClassLoader == null) throw new SqoopException(CoreError.CORE_0009, "No thread context classloader to override.")
		val classLoader: URLClassLoader = new URLClassLoader(urls.toArray, currentThreadClassLoader)
		Thread.currentThread.setContextClassLoader(classLoader)
	}

	def getJarsForProperty(classpathProperty: String): List[URL] = synchronized {
		val urls: ListBuffer[URL] = ListBuffer.empty
		val paths: String = config(classpathProperty)

		if (StringUtils.isEmpty(paths)) {
			LOG.debug("Property " + classpathProperty + " is null or empty.")
			return urls.toList
		}

		for (path: String <- paths.split(':').distinct) {
			try
				LOG.debug("Found jar in path: " + path)
				val url: URL = new File(path).toURI.toURL
				urls += url
				LOG.debug("Using URL: " + url.toString)
			catch {
				case e: MalformedURLException => {
					throw new SqoopException(CoreError.CORE_0009, "Malformed URL found.", e)
				}
			}
		}
		return urls.toList
	}

	def destroy():Unit = synchronized {
		if (provider != null) try
			provider.destroy()
		catch {
			case ex: Exception => {
				LOG.error("Failed to shutdown configuration provider", ex)
			}
		}
		provider = null
		configDir = null
		config = null
		oldConfig = null
		initialized = false
	}
}
