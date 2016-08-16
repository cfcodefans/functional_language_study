package org.apache.sqoop.model

import java.util.regex.Pattern

import org.apache.commons.lang.StringUtils
import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}
import org.apache.sqoop.model.InputEditable.InputEditable
import org.apache.sqoop.model.MInputType.MInputType
import org.apache.sqoop.utils.UrlSafeUtils

@InterfaceAudience.Public
@InterfaceStability.Unstable
object MMapInput {
	val SENSITIVE_VALUE_PLACEHOLDER: String = StringUtils.EMPTY
}

@InterfaceAudience.Public
@InterfaceStability.Unstable
class MMapInput(val name: String,
                val sensitive: Boolean,
                override val editable: InputEditable,
                val overrides: String,
                val _sensitiveKeyPattern: String,
                override val mValidators: List[MValidator])
	extends MInput[Map[String, String]](name, sensitive, editable, overrides, mValidators) {

	import MMapInput._

	private val sensitiveKeyPattern: String = _sensitiveKeyPattern

	def getUrlSafeValueString: String = {
		val valueMap: Map[String, String] = getValue
		if (valueMap == null) return null

		valueMap.map((kv) => {
			UrlSafeUtils.urlEncode(kv._1) + "=" + {
				if (kv._2 != null) UrlSafeUtils.urlEncode(kv._2) else ""
			}
		}).mkString("&")
	}

	def restoreFromUrlSafeValueString(valueString: String) {
		if (valueString == null) setValue(null)
		else {
			val valueMap: Map[String, String] = valueString.split("&")
				.map(pair => pair.split("="))
				.filter(_.length > 0)
				.map(pair => (UrlSafeUtils.urlDecode(pair(0)), {
					if (pair.length > 1 && pair(1) != null)
						UrlSafeUtils.urlDecode(pair(1))
					else ""
				})).toMap

			setValue(valueMap)
		}
	}

	def getType: MInputType = MInputType.MAP

	override def equals(other: Any): Boolean = {
		if (other.asInstanceOf[AnyRef] eq this) return true
		if (!other.isInstanceOf[MMapInput]) return false
		val mmi: MMapInput = other.asInstanceOf[MMapInput]
		getName == mmi.getName
	}

	override def hashCode: Int = 23 + 31 * getName.hashCode

	def isEmpty: Boolean = getValue == null

	def setEmpty() {
		setValue(null)
	}

	def getSensitiveKeyPattern: String = sensitiveKeyPattern

	def clone(cloneWithValue: Boolean): MMapInput = {
		val copy: MMapInput = new MMapInput(getName, isSensitive, getEditable, getOverrides, getSensitiveKeyPattern, getCloneOfValidators)
		copy.setPersistenceId(getPersistenceId)
		if (cloneWithValue && this.getValue != null) {
			copy.setValue(Map.empty[String, String] ++ this.getValue)
		}
		copy
	}

	def getNonsensitiveValue: Map[String, String] = {
		if (isEmpty) return null
		val sensitivePattern: Pattern = Pattern.compile(getSensitiveKeyPattern)

		return getValue.map((kv: (String, String)) => (kv._1, {
			if (sensitivePattern.matcher(kv._1).matches) SENSITIVE_VALUE_PLACEHOLDER
			else kv._2
		})).toMap

	}
}
