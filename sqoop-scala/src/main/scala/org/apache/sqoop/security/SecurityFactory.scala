package org.apache.sqoop.security

import org.apache.sqoop.common.SqoopException
import org.apache.sqoop.utils.ClassUtils

/**
  * Created by fan on 2016/8/14.
  */
object SecurityFactory {
	@throws[ClassNotFoundException]
	@throws[IllegalAccessException]
	@throws[InstantiationException]
	def getAuthenticationHandler(handler: String): AuthenticationHandler = {
		val handlerClass: Class[_] = ClassUtils.loadClass(handler)
		if (handlerClass == null) throw new SqoopException(SecurityError.AUTH_0004, "Authentication Handler Class is null: " + handler)
		var newHandler: AuthenticationHandler = null
		try
			newHandler = handlerClass.newInstance.asInstanceOf[AuthenticationHandler]
		catch {
			case ex: Exception => {
				throw new SqoopException(SecurityError.AUTH_0004, "Authentication Handler Class Exception: " + handler, ex)
			}
		}
		newHandler
	}

	@throws[ClassNotFoundException]
	@throws[IllegalAccessException]
	@throws[InstantiationException]
	def getAuthorizationHandler(handler: String): AuthorizationHandler = {
		val handlerClass: Class[_] = ClassUtils.loadClass(handler)
		if (handlerClass == null) throw new SqoopException(SecurityError.AUTH_0007, "Authorization Handler Class is null: " + handler)
		var newHandler: AuthorizationHandler = null
		try
			newHandler = handlerClass.newInstance.asInstanceOf[AuthorizationHandler]

		catch {
			case ex: Exception => {
				throw new SqoopException(SecurityError.AUTH_0007, "Authorization Handler Class Exception: " + handler, ex)
			}
		}
		newHandler
	}

	@throws[ClassNotFoundException]
	@throws[IllegalAccessException]
	@throws[InstantiationException]
	def getAuthenticationProvider(provider: String): AuthenticationProvider = {
		val providerClass: Class[_] = ClassUtils.loadClass(provider)
		if (providerClass == null) throw new SqoopException(SecurityError.AUTH_0010, "Authentication Provider Class is null: " + provider)
		var newProvider: AuthenticationProvider = null
		try
			newProvider = providerClass.newInstance.asInstanceOf[AuthenticationProvider]
		catch {
			case ex: Exception => {
				throw new SqoopException(SecurityError.AUTH_0010, "Authentication Provider Class is null: " + provider, ex)
			}
		}
		newProvider
	}
}
