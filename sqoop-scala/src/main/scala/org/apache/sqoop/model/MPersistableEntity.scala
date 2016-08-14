package org.apache.sqoop.model

import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}

/**
  * Represents a persistable metadata entity.
  */
@InterfaceAudience.Private
@InterfaceStability.Unstable
object MPersistableEntity {
	val PERSISTANCE_ID_DEFAULT: Long = -1L
}

@InterfaceAudience.Private
@InterfaceStability.Unstable
/**
  * Default constructor.
  */
abstract class MPersistableEntity protected() {
	private var persistenceId: Long = MPersistableEntity.PERSISTANCE_ID_DEFAULT

	/**
	  * Constructor building as a copy of other persistable entity.
	  *
	  * @param other Persistable entity to copy
	  */
	def this(other: MPersistableEntity) {
		this()
		this.persistenceId = other.persistenceId
	}

	def setPersistenceId(persistenceId: Long) {
		this.persistenceId = persistenceId
	}

	def getPersistenceId: Long = persistenceId

	def hasPersistenceId: Boolean = persistenceId != MPersistableEntity.PERSISTANCE_ID_DEFAULT

	override def toString: String
}
