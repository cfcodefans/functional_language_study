package org.apache.sqoop.security

import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}

/**
  * AuthenticationProvider is an abstract class for authentication. The
  * implementation should return userNames and groupNames.
  */
@InterfaceAudience.Public
@InterfaceStability.Unstable
trait AuthenticationProvider {
	def getUserName: String

	def getGroupNames: Array[String]
}
