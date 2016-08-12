package org.apache.sqoop.common

import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}

/**
  * Created by fan on 2016/8/11.
  */
@InterfaceAudience.Public
@InterfaceStability.Evolving
trait ErrorCode {
	def getCode: String

	def getMessage: String
}
