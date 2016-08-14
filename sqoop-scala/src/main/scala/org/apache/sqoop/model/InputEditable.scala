package org.apache.sqoop.model

/**
  * Various supported roles for editing input values that belong to a config
  * NOTE: In future this can be extended based on the roles supported in sqoop
  * for instance, we could have sqoop ADMIN_ONLY editable inputs
  */
object InputEditable extends Enumeration {
	type InputEditable = Value
	/**
	  * Sqoop user alone can edit the input values via rest API or shell command line
	  */
	val USER_ONLY,

	/**
	  * Connector code alone can edit the input values
	  */
	CONNECTOR_ONLY,

	/**
	  * Either Connector code or the sqoop user alone can edit the input values
	  */
	ANY = Value
}
