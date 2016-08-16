package org.apache.sqoop.model

import org.apache.commons.lang3.StringUtils
import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}
import org.apache.sqoop.common.SqoopException
import org.apache.sqoop.model.InputEditable.InputEditable
import org.apache.sqoop.model.MInputType.MInputType

@InterfaceAudience.Public
@InterfaceStability.Unstable
class MEnumInput(val name: String,
                 val sensitive: Boolean,
                 override val editable: InputEditable,
                 val overrides: String,
                 val _values: Array[String],
                 override val mValidators: List[MValidator])

	extends MInput[String](name, sensitive, editable, overrides, mValidators) {
	/**
	  * Array of available values
	  */
	private[model] var values: Array[String] = {
		if (_values != null) _values.clone else null
	}

	if (_values != null) this.values = _values.clone
	else this.values = null

	def getValues: Array[String] = {
		if (values != null) return values.clone
		new Array[String](0)
	}

	override def setValue(value: String) {
		// Null is allowed value
		if (value == null) {
			super.setValue(null)
			return
		}
		// However non null values must be available from given enumeration list
		for (allowedValue <- values) {
			if (allowedValue == value) {
				super.setValue(value)
				return
			}
		}
		// Otherwise we've got invalid value
		throw new SqoopException(ModelError.MODEL_008, "Invalid value " + value)
	}

	def setValue(value: Enum[_]) {
		setValue(value.toString)
	}

	def getUrlSafeValueString: String = getValue

	def restoreFromUrlSafeValueString(valueString: String) {
		setValue(valueString)
	}

	def getType: MInputType = MInputType.ENUM

	override def hasExtraInfo: Boolean = true

	override def getExtraInfoToString: String = StringUtils.join(values, ",")

	override def equals(other: Any): Boolean = {
		if (other.asInstanceOf[AnyRef] eq this) return true
		if (!other.isInstanceOf[MEnumInput]) return false
		val mei: MEnumInput = other.asInstanceOf[MEnumInput]
		getName == mei.getName && values == mei.values
	}

	override def hashCode: Int = {
		var hash: Int = 23 + 31 * getName.hashCode
		for (value <- values) {
			hash += 31 * value.hashCode
		}
		hash
	}

	def isEmpty: Boolean = getValue == null

	def setEmpty() {
		setValue(null.asInstanceOf[String])
	}

	def clone(cloneWithValue: Boolean): MEnumInput = {
		val copy: MEnumInput = new MEnumInput(getName, isSensitive, getEditable, getOverrides, getValues, getCloneOfValidators)
		copy.setPersistenceId(getPersistenceId)
		if (cloneWithValue) copy.setValue(this.getValue)
		copy
	}
}
