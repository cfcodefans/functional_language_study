package org.apache.sqoop.model

import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}
import org.apache.sqoop.validation.StatusEnum.Status
import org.apache.sqoop.validation.{Message, StatusEnum}

import scala.collection.mutable.ListBuffer

//import scala.collection.mutable._

/**
  * Element that can have associated validation messages (0..N).
  */
@InterfaceAudience.Private
@InterfaceStability.Unstable
abstract class MValidatedElement(other: MValidatedElement = null) extends MPersistableEntity(other) {
	private var mValidators: ListBuffer[MValidator] = null
	/**
	  * Validation messages.
	  */
	private var validationMessages: ListBuffer[Message] = null
	/**
	  * The worst status of all validation messages.
	  */
	private var validationStatus: Status = null

	def this(mValidators: List[MValidator]) {
		this()
		this.mValidators = ListBuffer.empty ++ mValidators
		resetValidationMessages()
	}

	def this(other: MValidatedElement) {
		this(other)
		resetValidationMessages()
		this.mValidators = ListBuffer.empty ++ other.getCloneOfValidators
		this.validationStatus = other.validationStatus
		this.validationMessages = ListBuffer.empty ++ other.validationMessages
		//		this.validationMessages.addAll(other.validationMessages)
	}

	/**
	  * Reset this validated element back to default state.
	  *
	  * Will remove all associated messages and validation status.
	  */
	def resetValidationMessages() {
		this.validationStatus = StatusEnum.getDefault
		this.validationMessages = ListBuffer.empty[Message]
	}

	/**
	  * Set validation messages (override anything that has been set before).
	  *
	  * @param msg Message itself
	  */
	def addValidationMessage(msg: Message) {
		this.validationMessages += msg
		this.validationStatus = StatusEnum.getWorstStatus(this.validationStatus, msg.getStatus)
	}

	/**
	  * Override all previously existing validation messages.
	  *
	  * @param messages
	  */
	def setValidationMessages(messages: List[Message]) {
		this.validationMessages = ListBuffer.empty ++ messages
		this.validationStatus = StatusEnum.getDefault
		for (message <- messages) {
			this.validationStatus = StatusEnum.getWorstStatus(this.validationStatus, message.getStatus)
		}
	}

	/**
	  * Return validation message for given severity.
	  *
	  * Return either associated message for given severity or null in case
	  * that there is no message with given severity.
	  */
	def getValidationMessages: List[Message] = this.validationMessages.toList

	def getValidators: List[MValidator] = mValidators.toList

	/**
	  * Return message validation status.
	  */
	def getValidationStatus: Status = validationStatus

	def getCloneOfValidators: List[MValidator] = {
		if (getValidators == null) return null
		return getValidators.map(_.clone(true).asInstanceOf[MValidator])
	}
}
