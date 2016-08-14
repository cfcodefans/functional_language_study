package org.apache.sqoop.security.authentication

import java.io.IOException

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.security.{SecurityUtil, UserGroupInformation}
import org.apache.logging.log4j.{LogManager, Logger}
import org.apache.sqoop.common.SqoopException
import org.apache.sqoop.core.SqoopConfiguration
import org.apache.sqoop.security.{AuthenticationHandler, SecurityConstants, SecurityError}

/**
  * Created by fan on 2016/8/14.
  */
object KerberosAuthenticationHandler {
	private[KerberosAuthenticationHandler] val LOG: Logger = LogManager.getLogger(KerberosAuthenticationHandler.getClass)
}

class KerberosAuthenticationHandler extends AuthenticationHandler {

	import KerberosAuthenticationHandler._

	/**
	  * Principal for Kerberos option value
	  */
	private var keytabPrincipal: String = null

	def getKeytabPrincipal: String = keytabPrincipal

	/**
	  * Keytab for Kerberos option value
	  */
	private var keytabFile: String = null

	def getKeytabFile: String = keytabFile

	def doInitialize() {
		securityEnabled = true
	}

	def secureLogin() {
		val mapContext: Map[String, String] = SqoopConfiguration.getInstance.getContext
		val keytab: String = mapContext(SecurityConstants.AUTHENTICATION_KERBEROS_KEYTAB).trim
		if (keytab.length == 0) throw new SqoopException(SecurityError.AUTH_0001, SecurityConstants.AUTHENTICATION_KERBEROS_KEYTAB)
		keytabFile = keytab
		val principal: String = mapContext(SecurityConstants.AUTHENTICATION_KERBEROS_PRINCIPAL).trim
		if (principal.length == 0) throw new SqoopException(SecurityError.AUTH_0002, SecurityConstants.AUTHENTICATION_KERBEROS_PRINCIPAL)
		keytabPrincipal = principal
		val conf: Configuration = new Configuration
		conf.set(get_hadoop_security_authentication, SecurityConstants.TYPE.KERBEROS.toString)
		UserGroupInformation.setConfiguration(conf)
		try
			val hostPrincipal: String = SecurityUtil.getServerPrincipal(principal, "0.0.0.0")
			UserGroupInformation.loginUserFromKeytab(hostPrincipal, keytab)
		catch {
			case ex: IOException => {
				throw new SqoopException(SecurityError.AUTH_0003, ex)
			}
		}
		LOG.info("Using Kerberos authentication, principal [" + principal + "] keytab [" + keytab + "]")
	}
}