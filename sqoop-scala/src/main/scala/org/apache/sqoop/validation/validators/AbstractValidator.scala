package org.apache.sqoop.validation.validators

import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}
import org.apache.sqoop.validation.StatusEnum._
import org.apache.sqoop.validation.{Message, StatusEnum}

import scala.collection.mutable._

/**
  * Abstract validator class.
  *
  * Can be used to validate inputs, forms and configuration classes.
  */
@InterfaceAudience.Public
@InterfaceStability.Unstable
object AbstractValidator {
	/**
	  * Default value of String argument.
	  */
	val DEFAULT_STRING_ARGUMENT: String = ""
}

@InterfaceAudience.Public
@InterfaceStability.Unstable
abstract class AbstractValidator[T]() {
	reset()

	/**
	  * Validation check.
	  *
	  * To be implemented by our children.
	  *
	  * @param instance Object to validate (depending on what we are validating)
	  */
	def validate(instance: T)

	/**
	  * Messages generated during validation.
	  */
	private var messages: ListBuffer[Message] = null
	/**
	  * Overall status of the validation.
	  */
	private var status: Status = null
	/**
	  * Optional String argument that can be defined for the Validator
	  */
	private var stringArgument: String = null

	protected def addMessage(msg: Message) {
		status = StatusEnum.getWorstStatus(status, msg.getStatus)
		messages += msg
	}

	protected def addMessage(status: Status, msg: String) {
		addMessage(new Message(status, msg))
	}

	def getMessages: List[Message] = messages.toList

	def getStatus: Status = status

	def setStringArgument(arg: String) {
		this.stringArgument = arg
	}

	def getStringArgument: String = stringArgument

	/**
	  * Reset validator state.
	  *
	  * * Previous messages
	  * * Status
	  * * Any stored arguments
	  */
	def reset() {
		messages = ListBuffer.empty[Message]
		status = StatusEnum.getDefault
		stringArgument = AbstractValidator.DEFAULT_STRING_ARGUMENT
	}
}
