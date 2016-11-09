package org.apache.sqoop.model

import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}

/**
  * Connectors will have configurations for FROM and TO.
  * If the connector is being used to extract data FROM,
  * then the connector type will be FROM. If the connector
  * is being used to load data TO, then the connector type
  * will be TO.
  */
@InterfaceAudience.Public
@InterfaceStability.Evolving
object Direction extends Enumeration {
	type Direction = Value
	val FROM, TO = Value
}
