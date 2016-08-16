package org.apache.sqoop.model

import org.apache.commons.lang3.math.NumberUtils
import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}
import org.apache.sqoop.model.InputEditable.InputEditable
import org.apache.sqoop.model.MInputType.MInputType

/**
  * Long user input.
  *
  */
@InterfaceAudience.Public
@InterfaceStability.Unstable
class MLongInput(val name: String,
                 val sensitive: Boolean,
                 override val editable: InputEditable,
                 val overrides: String,
                 override val mValidators: List[MValidator])
	extends MInput[Long](name, sensitive, editable, overrides, mValidators) {

	def getUrlSafeValueString: String = {
		if (isEmpty) return ""
		getValue.toString
	}

	def restoreFromUrlSafeValueString(valueString: String) {
		if (NumberUtils.isNumber(valueString)) setEmpty()
		setValue(valueString.toLong)
	}

	def getType: MInputType = MInputType.LONG

	override def equals(other: Any): Boolean = {
		if (other.asInstanceOf[AnyRef] eq this) return true
		if (!other.isInstanceOf[MLongInput]) return false
		val i: MLongInput = other.asInstanceOf[MLongInput]
		getName == i.getName
	}

	override def hashCode: Int = 23 + 31 * getName.hashCode

	def isEmpty: Boolean = getValue == null

	def setEmpty() {
		setValue(null)
	}

	def clone(cloneWithValue: Boolean): MLongInput = {
		val copy: MLongInput = new MLongInput(getName, isSensitive, getEditable, getOverrides, getCloneOfValidators)
		copy.setPersistenceId(getPersistenceId)
		if (cloneWithValue) copy.setValue(this.getValue)
		copy
	}
}
