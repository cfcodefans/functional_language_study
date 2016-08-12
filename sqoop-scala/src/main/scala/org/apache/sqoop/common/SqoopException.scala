package org.apache.sqoop.common

import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}

/**
  * Base exception for Sqoop driver. This exception requires the specification
  * of an error code for reference purposes. Where necessary the appropriate
  * constructor can be used to pass in additional message beyond what is
  * specified by the error code and/or the causal exception.
  */
@InterfaceAudience.Public
@InterfaceStability.Evolving
class SqoopException(message: String,
                     cause: Throwable = null) extends RuntimeException(message, cause) {
	private var originalMessage: String = _

	def getOriginalMessage = originalMessage

	private var code: ErrorCode = _

	def getErrorCode = code

	def this(_code: ErrorCode) = {
		this(_code.getCode + ":" + _code.getMessage)
		this.code = _code
		originalMessage = null
	}

	def this(code: ErrorCode, extraInfo: String) {
		this(code.getCode + ":" + code.getMessage + " - " + extraInfo)
		this.code = code
		originalMessage = extraInfo
	}

	def this(code: ErrorCode, cause: Throwable) {
		this(code.getCode + ":" + code.getMessage, cause)
		this.code = code
		originalMessage = null
	}

	def this(code: ErrorCode, extraInfo: String, cause: Throwable) {
		this(code.getCode + ":" + code.getMessage + " - " + extraInfo, cause)
		this.code = code
		originalMessage = extraInfo
	}
}
