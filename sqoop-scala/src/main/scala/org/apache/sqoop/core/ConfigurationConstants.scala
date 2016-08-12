package org.apache.sqoop.core

/**
  * Created by fan on 2016/8/11.
  */
object ConfigurationConstants {
	/**
	  * All configuration keys are prefixed with this:
	  * <tt>org.apache.sqoop.</tt>
	  */
	val PREFIX_GLOBAL_CONFIG: String = "org.apache.sqoop."

	/**
	  * All logging related configuration is prefixed with this:
	  * <tt>org.apache.sqoop.log4j.</tt>
	  */
	val PREFIX_LOG_CONFIG: String = PREFIX_GLOBAL_CONFIG + "log4j."

	/**
	  * Prefix for PropertiesConfigurationProvider implementation
	  */
	val PREFIX_PROPERTIES_PROVIDER_CONFIG = PREFIX_GLOBAL_CONFIG + "core.configuration.provider.properties."

	/**
	  * The system property that must be set for specifying the system
	  * configuration directory: <tt>sqoop.config.dir</tt>.
	  */
	val SYSPROP_CONFIG_DIR: String = "sqoop.config.dir"
	/**
	  * Bootstrap configuration property that specifies the system configuration
	  * provider: <tt>sqoop.config.provider</tt>.
	  */
	val BOOTCFG_CONFIG_PROVIDER: String = "sqoop.config.provider"
	/**
	  * Filename for the bootstrap configuration file:
	  * <tt>sqoop_bootstrap.properties</tt>.
	  */
	val FILENAME_BOOTCFG_FILE: String = "sqoop_bootstrap.properties"

	val FILENAME_CONNECTOR_PROPERTIES: String = "sqoopconnector.properties"

	val CONPROP_PROVIDER_CLASS: String = PREFIX_GLOBAL_CONFIG + "connector.class"

	val CONPROP_PROVIDER_NAME: String = PREFIX_GLOBAL_CONFIG + "connector.name"

	val PROPERTIES_PROVIDER_SLEEP: String = PREFIX_PROPERTIES_PROVIDER_CONFIG + "sleep"

	val CONNECTOR_AUTO_UPGRADE: String = PREFIX_GLOBAL_CONFIG + "connector.autoupgrade"

	val DRIVER_AUTO_UPGRADE: String = PREFIX_GLOBAL_CONFIG + "driver.autoupgrade"
	/**
	  * Add external jars to application classpath.
	  */
	val CLASSPATH: String = PREFIX_GLOBAL_CONFIG + "classpath.extra"
	/**
	  * Add external jars to job classpath.
	  */
	val JOB_CLASSPATH: String = PREFIX_GLOBAL_CONFIG + "classpath.job"
	/**
	  * List of connectors that will not be loaded at startup
	  */
	val BLACKLISTED_CONNECTORS: String = PREFIX_GLOBAL_CONFIG + ".connector.blacklist"
}
