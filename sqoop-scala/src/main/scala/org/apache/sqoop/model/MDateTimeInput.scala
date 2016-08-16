package org.apache.sqoop.model

import org.apache.commons.lang3.math.NumberUtils
import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}
import org.apache.sqoop.model.InputEditable.InputEditable
import org.apache.sqoop.model.MInputType.MInputType
import org.joda.time.DateTime

@InterfaceAudience.Public
@InterfaceStability.Unstable 
class MDateTimeInput(val name: String,
                     val sensitive: Boolean,
                     override val editable: InputEditable,
                     val overrides: String,
                     override val mValidators: List[MValidator])
	extends MInput[DateTime](name, sensitive, editable, overrides, mValidators) {

	def getUrlSafeValueString: String = {
		if (isEmpty) return ""
		String.valueOf(getValue.getMillis)
	}

	def restoreFromUrlSafeValueString(valueString: String) {
		if (!NumberUtils.isNumber(valueString)) setValue(null)
		else setValue(new DateTime(valueString.toLong))
	}

	def getType: MInputType = MInputType.DATETIME

	override def equals(other: Any): Boolean = {
		if (other.asInstanceOf[AnyRef].eq(this)) return true
		if (!other.isInstanceOf[MDateTimeInput]) return false
		val mdi: MDateTimeInput = other.asInstanceOf[MDateTimeInput]
		getName == mdi.getName
	}

	override def hashCode: Int = 23 + 31 * getName.hashCode

	def isEmpty: Boolean = getValue == null

	def setEmpty() {
		setValue(null)
	}

	def clone(cloneWithValue: Boolean): MDateTimeInput = {
		val copy: MDateTimeInput = new MDateTimeInput(getName, isSensitive, getEditable, getOverrides, getCloneOfValidators)
		copy.setPersistenceId(getPersistenceId)
		if (cloneWithValue && this.getValue != null) copy.setValue(new DateTime(this.getValue))
		copy
	}
}