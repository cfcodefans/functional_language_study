package org.apache.sqoop.model

import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}
import org.apache.sqoop.model.InputEditable.InputEditable
import org.apache.sqoop.model.MInputType.MInputType

/**
  * Represents a <tt>Boolean</tt> input.
  */
@InterfaceAudience.Public
@InterfaceStability.Unstable
class MBooleanInput(val name: String,
                    val sensitive: Boolean,
                    override val editable: InputEditable,
                    val overrides: String,
                    override val mValidators: List[MValidator])
	extends MInput[Boolean](name, sensitive, editable, overrides, mValidators) {

	def getUrlSafeValueString: String = getValue.toString

	def restoreFromUrlSafeValueString(valueString: String) {
		if ("true" == valueString) setValue(true)
		else setValue(false)
	}

	def getType: MInputType = MInputType.BOOLEAN

	override def equals(other: Any): Boolean = {
		if (!other.isInstanceOf[MBooleanInput]) return false
		val mbi: MBooleanInput = other.asInstanceOf[MBooleanInput]
		getName == mbi.getName && (isSensitive == mbi.isSensitive)
	}

	override def hashCode: Int = getValue.hashCode

	def isEmpty: Boolean = getValue == null

	def setEmpty() {
		setValue(null)
	}

	def clone(cloneWithValue: Boolean): Any = {
		val copy: MBooleanInput = new MBooleanInput(getName, isSensitive, getEditable, getOverrides, getCloneOfValidators)
		copy.setPersistenceId(getPersistenceId)
		if (cloneWithValue) copy.setValue(getValue)
		copy
	}
}
