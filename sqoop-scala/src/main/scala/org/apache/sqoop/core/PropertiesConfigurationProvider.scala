package org.apache.sqoop.core

import java.io._
import java.util.Properties
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.{Executors, ScheduledExecutorService, ScheduledFuture, TimeUnit}

import org.apache.logging.log4j.{LogManager, Logger}
import org.apache.sqoop.common.SqoopException
import org.apache.sqoop.core.ConfigurationConstants._
import org.apache.sqoop.error.code.CoreError

import scala.collection.JavaConversions._
import scala.collection.mutable._

/**
  * Created by fan on 2016/8/11.
  */
object PropertiesConfigurationProvider {
	private val LOG: Logger = LogManager.getLogger(PropertiesConfigurationProvider.getClass)
	val CONFIG_FILENAME: String = "sqoop.properties"
	val DEFAULT_SLEEP_TIME: Long = 60000

	private[PropertiesConfigurationProvider] val scheduler: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor

	private[PropertiesConfigurationProvider] def loadSleepTime(configuration: Map[String, String]): Long = {
		try {
			val value: String = configuration(PROPERTIES_PROVIDER_SLEEP)
			value.toLong
		} catch {
			case e: Exception => {
				LOG.debug("Can't load sleeping period from configuration file," + " using default value " + DEFAULT_SLEEP_TIME, e)
				DEFAULT_SLEEP_TIME
			}
		}
	}
}

class PropertiesConfigurationProvider extends ConfigurationProvider {

	import PropertiesConfigurationProvider._

	private val configuration: Map[String, String] = Map.empty
	private val listeners: ListBuffer[ConfigurationListener] = ListBuffer.empty

	private var configFile: File = null

	private class Loader extends Runnable {
		private[PropertiesConfigurationProvider] var lastUpdatedAt: Long = null

		private val shutdown: AtomicBoolean = new AtomicBoolean(false)

		private[PropertiesConfigurationProvider] def setShutdown: Unit = {
			shutdown.compareAndSet(false, true)
		}

		override def run(): Unit = {
			if (shutdown.get) return

			lastUpdatedAt = configFile.lastModified
			loadConfiguration(true)
			scheduler.schedule(this, loadSleepTime(configuration), TimeUnit.MICROSECONDS)
		}

		var future: ScheduledFuture[_] = null
	}

	private val loader: Loader = new Loader

	override def initialize(configDir: File, bootstrapConfiguration: Properties): Unit = synchronized({
		configFile = new File(configDir, CONFIG_FILENAME)
		if (!configFile.exists || !configFile.isFile || !configFile.canRead) throw new SqoopException(CoreError.CORE_0006, configFile.getPath)

		loader.lastUpdatedAt = configFile.lastModified
		loadConfiguration(false)

		loader.future = scheduler.schedule(loader, loadSleepTime(configuration), TimeUnit.MICROSECONDS)
	})

	override def destroy: Unit = {
		loader.setShutdown
		loader.future.cancel(true)
	}

	override def registerListener(listener: ConfigurationListener): Unit = synchronized({
		listeners += listener
	})

	override def getConfiguration: Map[String, String] = synchronized({
		configuration.clone
	})

	protected def loadProperties(propFile: File): Properties = {
		val configProperties: Properties = new Properties

		var fis: InputStream = null
		try {
			fis = new BufferedInputStream(new FileInputStream(propFile))
			configProperties.load(fis)
		} catch {
			case ex: Exception => {
				throw new SqoopException(CoreError.CORE_0006, propFile.getPath, ex)
			}
		} finally {
			if (fis != null) {
				try
					fis.close()
				catch {
					case ex: IOException => {
						LOG.error("Failed to close propFile stream for configuration", ex)
					}
				}
			}
		}
		configProperties
	}

	private def loadConfiguration(notifyListener: Boolean): Unit = synchronized({
		val configProperties: Properties = loadProperties(configFile)

		configuration.clear
		configProperties.keySet.foreach(k => configuration.put(k.toString, configProperties.get(k).toString))
		if (notifyListener) {
			listeners.foreach(_.configurationChanged)
		}
	})
}