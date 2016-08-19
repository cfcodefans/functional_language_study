package org.apache.sqoop.model


import org.apache.sqoop.model.InputEditable.InputEditable
import org.apache.sqoop.model.MInputType.MInputType

import scala.beans.BeanProperty

/**
  * Created by fan on 2016/8/16.
  */
abstract class MInput[T](name: String, _sensitive: Boolean, _editable: InputEditable, _overrides: String, mValidators: List[MValidator])
	extends MNamedElement(name, mValidators) with MClonable {
	private val sensitive: Boolean = _sensitive
	private val overrides: String = _overrides
	@BeanProperty val editable: InputEditable = _editable
	@BeanProperty var value: T = _

	def setValue(value: T): Unit = {
		this.value = value
	}

	/**
	  * @return the overrides attribute for the input
	  *         An input can override the value of one or more other inputs when edited
	  */
	def getOverrides: String = overrides

	/**
	  * @return <tt>true</tt> if this string represents sensitive information
	  */
	def isSensitive: Boolean = sensitive

	/**
	  * @return a URL-safe string representation of the value
	  */
	def getUrlSafeValueString: String

	/**
	  * Overrides the associated value of this input by the value represented by
	  * the provided URL-safe value string.
	  *
	  * @param valueString the string representation of the value from which the
	  *                    value must be restored.
	  */
	def restoreFromUrlSafeValueString(valueString: String)

	def getType: MInputType

	/**
	  * @return <tt>true</tt> if this _type maintains more state than what is
	  *         stored in the <tt>MInput</tt> base class.
	  */
	def hasExtraInfo: Boolean = false

	/**
	  * @return the string representation of state stored in this _type if
	  *         applicable or an empty string.
	  */
	def getExtraInfoToString: String = null

	/**
	  * All input types must override the <tt>equals()</tt> method such that the
	  * test for equality is based on static metadata only. As a result any
	  * set value, error message and other dynamic value data is not considered
	  * as part of the equality comparison.
	  */
	override def equals(other: Any): Boolean

	/**
	  * All input types must override the <tt>hashCode()</tt> method such that
	  * the hash code computation is solely based on static metadata. As a result
	  * any set value, error message and other dynamic value data is not
	  * considered as part of the hash code computation.
	  */
	override def hashCode: Int

	/**
	  * All input types must be able to tell if they contain some value or not.
	  *
	  * Empty values won't be serialized into metadata repository and will not be
	  * send across the wire between client and server.
	  *
	  * @return True if this input contains empty value.
	  */
	def isEmpty: Boolean

	/**
	  * Set Input value to empty value.
	  */
	def setEmpty()

	override def toString: String = {
		return s"input-${getName}:${getPersistenceId}:${getType}:${isSensitive}:${getEditable}:${getOverrides}" + {if (hasExtraInfo) getExtraInfoToString else ""}
	}
}
