package org.apache.sqoop.security

import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}
import org.apache.sqoop.common.SqoopException
import org.apache.sqoop.model.{MPrincipal, MPrivilege, MResource, MRole}

/**
  * AuthorizationHandler is responsible for controlling role based access.
  */
@InterfaceAudience.Public
@InterfaceStability.Unstable
abstract class AuthorizationHandler {
	def doInitialize(provider: AuthenticationProvider, serverName: String)

	/**
	  * Role related function
	  */
	@throws[SqoopException]
	def createRole(role: MRole)

	@throws[SqoopException]
	def dropRole(role: MRole)

	@throws[SqoopException]
	def getAllRoles: List[MRole]

	@throws[SqoopException]
	def getRolesByPrincipal(principal: MPrincipal): List[MRole]

	/**
	  * Principal related function
	  */
	@throws[SqoopException]
	def getPrincipalsByRole(role: MRole): List[MPrincipal]

	@throws[SqoopException]
	def grantRole(principals: List[MPrincipal], roles: List[MRole])

	@throws[SqoopException]
	def revokeRole(principals: List[MPrincipal], roles: List[MRole])

	/**
	  * Resource related function
	  */
	@throws[SqoopException]
	def updateResource(old_resource: MResource, new_resource: MResource)

	@throws[SqoopException]
	def removeResource(resource: MResource)

	/**
	  * Privilege related function
	  */
	@throws[SqoopException]
	def getPrivilegesByPrincipal(principal: MPrincipal, resource: MResource): List[MPrivilege]

	@throws[SqoopException]
	def grantPrivileges(principals: List[MPrincipal], privileges: List[MPrivilege])

	@throws[SqoopException]
	def revokePrivileges(principals: List[MPrincipal], privileges: List[MPrivilege])

	/**
	  * Validator related function
	  */
	@throws[SqoopException]
	def checkPrivileges(principal: MPrincipal, privileges: List[MPrivilege])
}
