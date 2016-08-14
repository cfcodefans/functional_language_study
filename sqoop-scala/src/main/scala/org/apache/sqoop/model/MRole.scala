package org.apache.sqoop.model

/**
  * Model describing entire role object which used in role based authorization controller
  */
class MRole(val name: String)

/**
  * Default constructor to build  new MRole model.
  *
  * param name Role name
  */ {

	def getName: String = name

	override def toString = s"Role(Role name: $name)"
}