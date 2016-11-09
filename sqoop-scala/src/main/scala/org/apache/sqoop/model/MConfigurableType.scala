package org.apache.sqoop.model

import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}

/**
  * Represents the sqoop entities that can own configs
  */
@InterfaceAudience.Private
@InterfaceStability.Unstable
object MConfigurableType extends Enumeration {
	type MConfigurableType = Value
	val CONNECTOR, DRIVER = Value
}
