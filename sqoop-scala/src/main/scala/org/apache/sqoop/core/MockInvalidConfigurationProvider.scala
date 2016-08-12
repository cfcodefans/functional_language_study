package org.apache.sqoop.core

import java.io.File
import java.util
import java.util.Properties

/**
  * Created by fan on 2016/8/11.
  */
class MockInvalidConfigurationProvider() extends ConfigurationProvider {
	{
		throw new RuntimeException("Cannot instantiate")
	}

	override def initialize(configDir: File, bootstrapConfiguration: Properties): Unit = {}

	override def destory: Unit = {}

	override def registerListener(listener: ConfigurationListener): Unit = {}

	override def getConfiguration: util.Map[String, String] = null
}
