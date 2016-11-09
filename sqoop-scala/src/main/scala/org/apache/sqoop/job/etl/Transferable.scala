package org.apache.sqoop.job.etl

import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}

/**
  * This entity encapsulates the workflow for data transfer via the
  * {@link SqoopConnector}.It basically acts as an adapter between the data-source
  * imported from or exported to.
  */
@InterfaceAudience.Public
@InterfaceStability.Evolving
abstract class Transferable(var initializer: Class[_ <: Initializer[_, _]], var destroyer: Class[_ <: Destroyer[_, _]]) {
	def getDestroyer: Class[_ <: Destroyer[_, _]] = destroyer

	def getInitializer: Class[_ <: Initializer[_, _]] = initializer

	override def toString: String = "initializer=" + initializer.getName + ", destroyer=" + destroyer.getName
}
