package org.apache.sqoop.core

/**
  * Interface that make Sqoop Server components sensitive to
  * configuration file changes at the runtime
  */
trait Reconfigurable {
	/**
	  * Method to notify each reconfigurable components
	  */
	def configurationChanged
}
