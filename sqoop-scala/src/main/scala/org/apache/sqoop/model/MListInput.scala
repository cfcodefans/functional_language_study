package org.apache.sqoop.model

import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}
import org.apache.sqoop.model.InputEditable.InputEditable
import org.apache.sqoop.model.MInputType.MInputType
import org.apache.sqoop.utils.UrlSafeUtils

import scala.collection.mutable.ListBuffer

@InterfaceAudience.Public
@InterfaceStability.Unstable
class MListInput(val name: String,
                 val sensitive: Boolean,
                 override val editable: InputEditable,
                 val overrides: String,
                 override val mValidators: List[MValidator])
	extends MInput[List[String]](name, sensitive, editable, overrides, mValidators) {

	def getUrlSafeValueString: String = {
		val valueList: List[String] = getValue
		if (valueList == null) return null
		var first: Boolean = true

		return valueList.map(UrlSafeUtils.urlEncode(_)).mkString("&")
	}

	def restoreFromUrlSafeValueString(valueString: String) {
		if (valueString == null) setValue(null)
		else {
			setValue(valueString.split("&").map(UrlSafeUtils.urlDecode(_)).toList)
		}
	}

	def getType: MInputType = MInputType.LIST

	override def equals(other: Any): Boolean = {
		if (other.asInstanceOf[AnyRef] eq this) return true
		if (!other.isInstanceOf[MListInput]) return false
		val mli: MListInput = other.asInstanceOf[MListInput]
		getName == mli.getName
	}

	override def hashCode: Int = 23 + 31 * getName.hashCode

	def isEmpty: Boolean = getValue == null

	def setEmpty() {
		setValue(null)
	}

	def clone(cloneWithValue: Boolean): Any = {
		val copy: MListInput = new MListInput(getName, isSensitive, getEditable, getOverrides, getCloneOfValidators)
		copy.setPersistenceId(getPersistenceId)

		if (cloneWithValue && this.getValue != null) {
			copy.setValue((ListBuffer.empty ++ this.getValue).toList)
		}
		copy
	}
}
