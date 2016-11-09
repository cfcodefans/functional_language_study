package org.apache.sqoop.model

import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}

/**
  * Represents the various config types supported by the system.
  */
@InterfaceAudience.Private
@InterfaceStability.Unstable
object MConfigTypeEnum extends Enumeration {

	class MConfigType(_subTypes: String*) extends Val {
		val subTypes: Set[String] = _subTypes.toSet
	}

	/** Unknown config _type */
	val OTHER = new MConfigType
	//	@deprecated val // NOTE: only exists to support the connector data upgrade path
	val CONNECTION = new MConfigType
	/** link config _type */
	val LINK = new MConfigType("link")
	/** Job config _type */
	// NOTE: cannot use the constants declared below since it is not declared yet
	// compiler restriction
	val JOB = new MConfigType("from", "to", "driver")

	def getSubTypes(_type: MConfigType): Set[String] = _type.subTypes

	val FROM_SUB_TYPE: String = "from"
	val TO_SUB_TYPE: String = "to"
	val DRIVER_SUB_TYPE: String = "driver"
}