package org.apache.sqoop.security

import org.apache.logging.log4j.{LogManager, Logger}
import org.apache.sqoop.core.{Reconfigurable, SqoopConfiguration}


/**
  * AuthorizationManager is responsible for managing AuthorizationHandler.
  */

object AuthorizationManager {
	/**
	  * Default authorization handler
	  */
	val DEFAULT_AUTHORIZATION_HANDLER: String = "org.apache.sqoop.security.authorization.DefaultAuthorizationHandler"
	/**
	  * Default authentication provider
	  */
	val DEFAULT_AUTHENTICATION_PROVIDER: String = "org.apache.sqoop.security.authorization.DefaultAuthenticationProvider"
	/**
	  * Default authentication provider
	  */
	val DEFAULT_SERVER_NAME: String = "SqoopServer1"
	/**
	  * Private instance to singleton of this class.
	  */
	private var instance: AuthorizationManager = new AuthorizationManager

	/**
	  * Return current instance.
	  *
	  * @return Current instance
	  */
	def getInstance: AuthorizationManager = instance

	/**
	  * Allows to set instance in case that it's need.
	  * <p/>
	  * This method should not be normally used as the default instance should be sufficient. One target
	  * user use case for this method are unit tests.
	  *
	  * @param newInstance New instance
	  */
	def setInstance(newInstance: AuthorizationManager) {
		instance = newInstance
	}

	private[AuthorizationManager] val LOG: Logger = LogManager.getLogger(AuthorizationManager.getClass)

}

class AuthorizationManager private () extends Reconfigurable {
	import AuthorizationManager._

	def destroy() {
		LOG.trace("Begin authorization manager destroy")
	}

	/**
	  * Private Authorization Handler to singleton of this class.
	  */
	private var authorizationHandler: AuthorizationHandler = null

	/**
	  * Return current authorization handler.
	  *
	  * @return Current authorization handler
	  */
	def getAuthorizationHandler: AuthorizationHandler = authorizationHandler

	def configurationChanged() {
		LOG.info("Begin authorization manager reconfiguring")
		// If there are configuration options for AuthorizationManager,
		// implement the reconfiguration procedure right here.
		LOG.info("Authorization manager reconfigured")
	}

	@throws[ClassNotFoundException]
	@throws[IllegalAccessException]
	@throws[InstantiationException]
	def initialize() {
		LOG.trace("Begin authorization manager initialization")
		val handler: String = SqoopConfiguration.getInstance.getContext.getOrElse(SecurityConstants.AUTHORIZATION_HANDLER, DEFAULT_AUTHORIZATION_HANDLER).trim
		authorizationHandler = SecurityFactory.getAuthorizationHandler(handler)
		val provider: String = SqoopConfiguration.getInstance.getContext.getOrElse(SecurityConstants.AUTHENTICATION_PROVIDER, DEFAULT_AUTHENTICATION_PROVIDER).trim
		val serverName: String = SqoopConfiguration.getInstance.getContext.getOrElse(SecurityConstants.SERVER_NAME, DEFAULT_SERVER_NAME).trim
		authorizationHandler.doInitialize(SecurityFactory.getAuthenticationProvider(provider), serverName)
		LOG.info("Authorization loaded.")
	}
}
