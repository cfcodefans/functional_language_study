package org.apache.sqoop.model

import java.util.Locale

/**
  * Model describing entire resource object which used in resource based authorization controller
  */
object MResource {

	object TYPE extends Enumeration {
		type TYPE = Value
		val SERVER, CONNECTOR, LINK, JOB = Value
	}

}

class MResource(val name: String,

                /**
                  * Currently, the _type supports connector, link, job and submission.
                  */
                val _type: MResource.TYPE.TYPE)

/**
  * Default constructor to build  new MResource model.
  *
  * param name Resource name
  * param _type Resource _type
  */ {
	/**
	  * constructor to build  new MResource model.
	  *
	  * @param name     Resource name
	  * @param typeName Resource _type name
	  */
	def this(name: String, typeName: String) {
		this(name, MResource.TYPE.withName(typeName.toUpperCase(Locale.getDefault)))
	}


	def getName: String = name

	def getType: String = _type.toString

	override def toString = s"Resource (Resource name: $name, Resource _type: $_type)"
}

