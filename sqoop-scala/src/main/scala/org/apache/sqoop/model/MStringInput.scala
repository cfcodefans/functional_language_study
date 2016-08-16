package org.apache.sqoop.model

import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}
import org.apache.sqoop.model.InputEditable.InputEditable
import org.apache.sqoop.model.MInputType.MInputType
import org.apache.sqoop.utils.UrlSafeUtils

/**
  * Represents a <tt>String</tt> input. The boolean flag <tt>sensitive</tt> supplied
  * to its constructor can be used to indicate if the string should be masked
  * from user-view. This is helpful for creating input strings that represent
  * sensitive information such as passwords.
  */
/**
  * @param name      the parameter name
  * @param sensitive a flag indicating if the string should be masked
  * @param maxLength the maximum length of the string
  */
@InterfaceAudience.Public
@InterfaceStability.Unstable
final class MStringInput(val name: String,
                         val sensitive: Boolean,
                         override val editable: InputEditable,
                         val overrides: String,
                         val maxLength: Short,
                         override val mValidators: List[MValidator])
	extends MInput[String](name, sensitive, editable, overrides, mValidators) {
	/**
	  * @return the maximum length of this string type
	  */
	def getMaxLength: Short = maxLength

	def getUrlSafeValueString: String = UrlSafeUtils.urlEncode(getValue)

	def restoreFromUrlSafeValueString(valueString: String) {
		setValue(UrlSafeUtils.urlDecode(valueString))
	}

	def getType: MInputType = MInputType.STRING

	override def hasExtraInfo: Boolean = true

	override def getExtraInfoToString: String = getMaxLength.toString

	override def equals(other: Any): Boolean = {
		if (other.asInstanceOf[AnyRef] eq this) return true
		if (!other.isInstanceOf[MStringInput]) return false
		val msi: MStringInput = other.asInstanceOf[MStringInput]
		getName == msi.getName && (isSensitive == msi.isSensitive) && (maxLength == msi.maxLength)
	}

	override def hashCode: Int = {
		var result: Int = 23 + 31 * getName.hashCode
		result = 31 * result + (if (isSensitive) 1 else 0)
		result = 31 * result + maxLength
		result
	}

	def isEmpty: Boolean = getValue == null

	def setEmpty() {
		setValue(null)
	}

	def clone(cloneWithValue: Boolean): MStringInput = {
		val copy: MStringInput = new MStringInput(getName, isSensitive, getEditable, getOverrides, getMaxLength, getCloneOfValidators)
		copy.setPersistenceId(getPersistenceId)
		if (cloneWithValue) copy.setValue(this.getValue)
		copy
	}
}

