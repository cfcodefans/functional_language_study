package org.apache.sqoop.core

import org.apache.logging.log4j.{LogManager, Logger}
import org.apache.sqoop.security.{AuthenticationManager, AuthorizationManager}

/**
  * Created by fan on 2016/8/12.
  */
object SqoopServer {
	private[SqoopServer] val LOG: Logger = LogManager.getLogger(SqoopServer.getClass)

	def initialize() {
		try
			LOG.info("Initializing Sqoop server.")
			SqoopConfiguration.getInstance.initialize()
			AuthenticationManager.getInstance.initialize()
			AuthorizationManager.getInstance.initialize()
//			AuditLoggerManager.getInstance.initialize()
//			RepositoryManager.getInstance.initialize()
//			MasterKeyManager.getInstance.initialize()
//			ConnectorManager.getInstance.initialize()
//			Driver.getInstance.initialize()
//			JobManager.getInstance.initialize()
			LOG.info("Sqoop server has successfully been initialized.")
		catch {
			case e: Any => {
				LOG.error("Failure in server initialization", e)
				throw new RuntimeException("Failure in server initialization", e)
			}
		}
	}

	def destroy() {
		LOG.info("Shutting down Sqoop server")
//		JobManager.getInstance.destroy()
//		Driver.getInstance.destroy()
//		ConnectorManager.getInstance.destroy()
//		MasterKeyManager.getInstance.destroy()
//		RepositoryManager.getInstance.destroy()
//		AuditLoggerManager.getInstance.destroy()
		AuthorizationManager.getInstance.destroy()
		AuthenticationManager.getInstance.destroy()
		SqoopConfiguration.getInstance.destroy()
//		ClassUtils.clearCache()
		LOG.info("Sqoop server has been correctly terminated")
	}
}
