package org.apache.sqoop.model

import java.util.Locale

/**
  * Model describing entire principal object which used in principal based authorization controller
  */
object MPrincipal {

	/**
	  * Currently, the type supports user, group and role.
	  */
	object TYPE extends Enumeration {
		type TYPE = Value
		val USER, GROUP, ROLE = Value
	}

	type _TYPE = TYPE.TYPE
}

/**
  * Default constructor to build  new MPrincipal model.
  *
  * param name  Principal name
  * param _type Principal type
  */

class MPrincipal(val name: String, val _type: MPrincipal._TYPE) {
	/**
	  * constructor to build  new MPrincipal model.
	  *
	  * @param name     Principal name
	  * @param typeName Principal type name
	  */
	def this(name: String, typeName: String) {
		this(name, MPrincipal.TYPE.withName(typeName.toUpperCase(Locale.getDefault)))
	}

	override def toString: String = {
		return s"Principal (Principal name: ${this.name},  Principal type: ${this._type})"
	}

	def getName: String = name

	def getType: String = _type.toString
}
