package org.apache.sqoop.model

import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}
import org.apache.sqoop.common.SqoopException

/**
  * Represents a group of inputs that are processed together. This allows the
  * input gathering process to be broken down into multiple steps that can be
  * then paged through by the user interface.
  */
@InterfaceAudience.Public
@InterfaceStability.Unstable
final class MConfig(val name: String, val inputs: List[MInput[_]], val mValidators: List[MValidator]) extends MNamedElement(name, mValidators) with MClonable {
	if (inputs != null && inputs.size > 0) {
		import scala.collection.JavaConversions._
		for (input <- inputs) {
			inputNames.add(input.getName)
			if (input.getEditable == InputEditable.USER_ONLY) userOnlyEditableInputNames.add(input.getName)
		}
	}
	private val inputNames: util.Set[String] = new util.HashSet[String]
	private val userOnlyEditableInputNames: util.Set[String] = new util.HashSet[String]

	def getInputs: List[MInput[_]] = inputs

	def getInputNames: util.Set[String] = inputNames

	def getUserOnlyEditableInputNames: util.Set[String] = userOnlyEditableInputNames

	def getInput(inputName: String): MInput[_] = {
		import scala.collection.JavaConversions._
		for (input <- inputs) {
			if (inputName == input.getName) return input
		}
		throw new SqoopException(ModelError.MODEL_011, "Input name: " + inputName)
	}

	def getStringInput(inputName: String): MStringInput = getInput(inputName).asInstanceOf[MStringInput]

	def getEnumInput(inputName: String): MEnumInput = getInput(inputName).asInstanceOf[MEnumInput]

	def getIntegerInput(inputName: String): MIntegerInput = getInput(inputName).asInstanceOf[MIntegerInput]

	def getLongInput(inputName: String): MLongInput = getInput(inputName).asInstanceOf[MLongInput]

	def getBooleanInput(inputName: String): MBooleanInput = getInput(inputName).asInstanceOf[MBooleanInput]

	def getMapInput(inputName: String): MMapInput = getInput(inputName).asInstanceOf[MMapInput]

	def getListInput(inputName: String): MListInput = getInput(inputName).asInstanceOf[MListInput]

	def getDateTimeInput(inputName: String): MDateTimeInput = getInput(inputName).asInstanceOf[MDateTimeInput]

	override def toString: String = {
		val sb: StringBuilder = new StringBuilder("config-").append(getName)
		sb.append(":").append(getPersistenceId).append(":").append(inputs)
		sb.toString
	}

	override def equals(other: Any): Boolean = {
		if (other eq this) return true
		if (!other.isInstanceOf[MConfig]) return false
		val mf: MConfig = other.asInstanceOf[MConfig]
		getName == mf.getName && inputs == mf.inputs
	}

	override def hashCode: Int = {
		var result: Int = 17
		result = 31 * result + getName.hashCode
		import scala.collection.JavaConversions._
		for (mi <- inputs) {
			result = 31 * result + mi.hashCode
		}
		result
	}

	def clone(cloneWithValue: Boolean): MConfig = {
		val copyInputs: List[MInput[_]] = new util.ArrayList[MInput[_]]
		import scala.collection.JavaConversions._
		for (itr <- this.getInputs) {
			copyInputs.add(itr.clone(cloneWithValue).asInstanceOf[MInput[_]])
		}
		val copyConfig: MConfig = new MConfig(this.getName, copyInputs, getCloneOfValidators)
		copyConfig
	}
}
