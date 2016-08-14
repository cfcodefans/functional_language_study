package org.apache.sqoop.security.authentication

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.security.UserGroupInformation
import org.apache.logging.log4j.{LogManager, Logger}
import org.apache.sqoop.security.{AuthenticationHandler, SecurityConstants}

/**
  * Created by fan on 2016/8/14.
  */
object SimpleAuthenticationHandler {
	private[SimpleAuthenticationHandler] val LOG: Logger = LogManager.getLogger(SimpleAuthenticationHandler.getClass)
}

class SimpleAuthenticationHandler extends AuthenticationHandler {

	import SimpleAuthenticationHandler._

	def doInitialize() {
		securityEnabled = false
	}

	def secureLogin() {
		//no secureLogin, just set configurations
		val conf: Configuration = new Configuration
		conf.set(get_hadoop_security_authentication, SecurityConstants.TYPE.SIMPLE.toString)
		UserGroupInformation.setConfiguration(conf)
		LOG.info("Using simple/pseudo authentication, principal [" + System.getProperty("user.name") + "]")
	}
}
