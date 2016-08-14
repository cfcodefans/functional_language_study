package org.apache.sqoop.validation

import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}
import org.apache.sqoop.validation.StatusEnum._

/**
  * Validation message.
  *
  * Validation message have always two parts - severity and textual information about what
  * is wrong. It can be associated with Input, Config or ConfigurationGroup class.
  */
@InterfaceAudience.Public
@InterfaceStability.Unstable class Message(var status: Status, var message: String) {
	def getStatus: Status = status

	def getMessage: String = message

	override def toString: String = "{" + status + ": " + message + "}"

	def canEqual(other: Any): Boolean = other.isInstanceOf[Message]

	override def equals(other: Any): Boolean = other match {
		case that: Message =>
			(that canEqual this) &&
				status == that.status &&
				message == that.message
		case _ => false
	}

	override def hashCode(): Int = {
		val state = Seq(status, message)
		state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
	}
}
