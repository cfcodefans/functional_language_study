package org.apache.sqoop.model

import org.apache.commons.lang3.math.NumberUtils
import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}
import org.apache.sqoop.model.InputEditable.InputEditable
import org.apache.sqoop.model.MInputType.MInputType

/**
  * Integer base user input.
  *
  * This input is able to process empty (NULL) value.
  */
@InterfaceAudience.Public
@InterfaceStability.Unstable
class MIntegerInput(val name: String,
                    val sensitive: Boolean,
                    override val editable: InputEditable,
                    val overrides: String,
                    override val mValidators: List[MValidator])
	extends MInput[Integer](name, sensitive, editable, overrides, mValidators) {

	def getUrlSafeValueString: String = {
		if (isEmpty) return ""
		getValue.toString
	}

	def restoreFromUrlSafeValueString(valueString: String) {
		if (NumberUtils.isNumber(valueString)) setEmpty()
		setValue(Integer.valueOf(valueString))
	}

	def getType: MInputType = MInputType.INTEGER

	override def equals(other: Any): Boolean = {
		if (other.asInstanceOf[AnyRef] eq this) return true
		if (!other.isInstanceOf[MIntegerInput]) return false
		val i: MIntegerInput = other.asInstanceOf[MIntegerInput]
		getName == i.getName
	}

	override def hashCode: Int = 23 + 31 * getName.hashCode

	def isEmpty: Boolean = getValue == null

	def setEmpty() {
		setValue(null)
	}

	def clone(cloneWithValue: Boolean): MIntegerInput = {
		val copy: MIntegerInput = new MIntegerInput(getName, isSensitive, getEditable, getOverrides, getCloneOfValidators)
		copy.setPersistenceId(getPersistenceId)
		if (cloneWithValue) copy.setValue(this.getValue)
		copy
	}
}

