package org.apache.sqoop.model

import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}

/**
  * Represents the various input types supported by the system.
  */
@InterfaceAudience.Private
@InterfaceStability.Unstable object MInputType extends Enumeration {
	type MInputType = Value
	val OTHER, STRING, MAP, INTEGER, BOOLEAN, ENUM, LONG, LIST, DATETIME = Value
}