package org.apache.sqoop.core

import java.io.File
import java.util.Properties

/**
  * Created by fan on 2016/8/11.
  */
trait ConfigurationProvider {
	def initialize(configDir: File, bootstrapConfiguration: Properties): Unit

	def destroy(): Unit

	def registerListener(listener: ConfigurationListener): Unit

	def getConfiguration: Map[String, String]
}
