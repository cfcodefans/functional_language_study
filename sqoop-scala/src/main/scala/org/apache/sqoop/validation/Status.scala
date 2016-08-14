package org.apache.sqoop.validation

import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}

/**
  * Severity of validation message.
  *
  * OK:
  * Everything is correct (default state).
  *
  * WARNING:
  * Warning is something suspicious, potentially wrong but something that
  * can be ignored. For example in case of JDBC URL element, warning would
  * be if specified host is not responding - it's warning because specified
  * URL might be wrong. However at the same time URL might be right as only
  * target host might be down.
  *
  * ERROR:
  * Error represents unacceptable element content. For example in case of JDBC
  * URL path, error would be empty element or element containing invalid URL.
  */
@InterfaceAudience.Public
@InterfaceStability.Unstable
object StatusEnum extends Enumeration {

	class Status(id: Int, name: String) extends Val(id, name) {
		val canProceed: Boolean = this == OK || this == WARNING
	}

	/**
	  * There are no issues, no warnings. Everything is correct.
	  */
	val OK,

	/**
	  * Validated entity is correct enough to be processed. There might be some
	  * warnings, but no errors.
	  */
	WARNING,

	/**
	  * There are serious issues with validated entity. We can't proceed until
	  * reported issues will be resolved.
	  */
	ERROR = new Status(nextId, if (nextName != null && nextName.hasNext) nextName.next() else null)

	/**
	  * Compare multiple statuses and return the worst one.
	  *
	  * @param statuses Multiple statuses
	  * @return The worst status
	  */
	def getWorstStatus(statuses: Status*): Status = {
		var finalStatus: Status = OK
		for (status <- statuses) {
			if (finalStatus.compareTo(status) < 1) finalStatus = status
		}
		finalStatus
	}

	/**
	  * Return default validation "everything is completely fine".
	  *
	  * @return Default validation status
	  */
	def getDefault: Status = OK
}
