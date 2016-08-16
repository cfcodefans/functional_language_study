package org.apache.sqoop.model

import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}

/**
  * Represents an @Validator class by its name and optional argument
  */
@InterfaceAudience.Public
@InterfaceStability.Unstable
class MValidator(val validatorClass: String, val strArg: String) extends MClonable {
	// The value of cloneWithValue is ignored
	def clone(cloneWithValue: Boolean): Any = new MValidator(validatorClass, strArg)

	def getValidatorClass: String = validatorClass

	def getStrArg: String = strArg

	def canEqual(other: Any): Boolean = other.isInstanceOf[MValidator]

	override def equals(other: Any): Boolean = other match {
		case that: MValidator =>
			(that canEqual this) &&
				validatorClass == that.validatorClass &&
				strArg == that.strArg
		case _ => false
	}

	override def hashCode(): Int = {
		val state = Seq(validatorClass, strArg)
		state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
	}
}

