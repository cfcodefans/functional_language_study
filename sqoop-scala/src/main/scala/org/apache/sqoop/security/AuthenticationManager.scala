package org.apache.sqoop.security

import org.apache.logging.log4j.{LogManager, Logger}
import org.apache.sqoop.core.{Reconfigurable, SqoopConfiguration}

import scala.beans.BeanProperty

/**
  * Created by fan on 2016/8/14.
  */
object AuthenticationManager {
	private[AuthenticationManager] val LOG: Logger = LogManager.getLogger(AuthenticationManager.getClass)


	/**
	  * Default authentication handler
	  */
	val DEFAULT_AUTHENTICATION_HANDLER: String = "org.apache.sqoop.security.authentication.SimpleAuthenticationHandler"

	@BeanProperty var instance: AuthenticationManager = new AuthenticationManager
}

class AuthenticationManager extends Reconfigurable {

	import AuthenticationManager._

	/**
	  * Method to notify each reconfigurable components
	  */
	def configurationChanged() {
		LOG.info("Begin authentication manager reconfiguring")
		// If there are configuration options for AuthenticationManager,
		// implement the reconfiguration procedure right here.
		LOG.info("Authentication manager reconfigured")
	}

	@throws[Exception]
	def initialize(): Unit = synchronized({
		if (LOG.isTraceEnabled)
			LOG.trace("Begin authentication manager initialization")

		//TODO SecurityFactory
		val handler: String = SqoopConfiguration.getInstance.getContext.getOrElse(SecurityConstants.AUTHENTICATION_HANDLER, DEFAULT_AUTHENTICATION_HANDLER).trim
		authenticationHandler = SecurityFactory.getAuthenticationHandler(handler)

		authenticationHandler.doInitialize()
		authenticationHandler.secureLogin()

		if (LOG.isInfoEnabled) LOG.info("Authentication loaded.")
	})

	def destroy(): Unit = synchronized {
		LOG.trace("Begin authentication manager destroy")
	}

	/**
	  * Private Authentication Handler to singleton of this class.
	  */
	private var authenticationHandler: AuthenticationHandler = null

	/**
	  * Return current authentication handler.
	  *
	  * @return Current authentication handler
	  */
	def getAuthenticationHandler: AuthenticationHandler = authenticationHandler
}
