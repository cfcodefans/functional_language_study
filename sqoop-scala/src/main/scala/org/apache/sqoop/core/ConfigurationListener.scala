package org.apache.sqoop.core

/**
  * Created by fan on 2016/8/11.
  */
trait ConfigurationListener {
	def configurationChanged:Unit
}
