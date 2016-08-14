package org.apache.sqoop.model

import java.util.Locale

/**
  * Model describing entire privilege object which used in privilege based authorization controller
  */
object MPrivilege {

	object ACTION extends Enumeration {
		type ACTION = Value
		val ALL, READ, WRITE = Value
	}

}

/**
  * Default constructor to build  new MPrivilege model.
  *
  * @param resource          Privilege resource
  * @param action            Privilege action
  * @param with_grant_option Privilege with_grant_option
  */
class MPrivilege(val resource: MResource,

                 /**
                   * Currently, the action supports view, use, create, update, delete and enable_disable.
                   */
                 val action: MPrivilege.ACTION.ACTION, val with_grant_option: Boolean) {
	/**
	  * constructor to build  new MPrivilege model.
	  *
	  * @param resource          Privilege resource
	  * @param actionName        Privilege action name
	  * @param with_grant_option Privilege with_grant_option
	  */
	def this(resource: MResource, actionName: String, with_grant_option: Boolean) {
		this(resource, MPrivilege.ACTION.withName(actionName.toUpperCase(Locale.getDefault)), with_grant_option)
	}

	override def toString: String = {
		return s"Privilege (Privilege resource: ${resource}, Privilege action: $action, Privilege with_grant_option: $with_grant_option)"
	}

	def getResource: MResource = resource

	def getAction: String = action.toString

	def isWith_grant_option: Boolean = with_grant_option
}
