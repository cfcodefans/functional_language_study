package org.apache.sqoop.security

import org.apache.logging.log4j.{LogManager, Logger}
import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}

object AuthenticationHandler {
	private[AuthenticationHandler] val LOG: Logger = LogManager.getLogger(AuthenticationHandler.getClass)

}

/** *
  * AuthenticationHandler is responsible for secure checking.
  * KerberosAuthenticationHandler and SimpleAuthenticationHandler have
  * implemented this abstract class.
  */
@InterfaceAudience.Public
@InterfaceStability.Unstable
abstract class AuthenticationHandler {
	/**
	  * Security enabled option value
	  */
	protected var securityEnabled: Boolean = false

	def isSecurityEnabled: Boolean = securityEnabled

	def doInitialize()

	def secureLogin()

	def get_hadoop_security_authentication: String = "hadoop.security.authentication"

	/**
	  * AuthenticationProvider is an authentication to get userNames and groupNames.
	  */
	protected var authenticationProvider: AuthenticationProvider = null

	def getAuthenticationProvider: AuthenticationProvider = authenticationProvider
}
