package org.apache.sqoop.model

import org.apache.commons.lang3.StringUtils
import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}
import org.apache.sqoop.model.Direction.{Direction, _}

/**
  * Represents which Directions are supported.
  */
@InterfaceAudience.Public
@InterfaceStability.Unstable
object SupportedDirections {
	private val SUPPORTED_DIRECTIONS_SEPARATOR: Char = '/'

	def fromString(supportedDirections: String): SupportedDirections = {
		if (StringUtils.isBlank(supportedDirections))
			return new SupportedDirections(false, false)
		val option: Option[String] = supportedDirections.split("/")
			.find(direction => FROM.toString == direction || TO.toString == direction)
		return new SupportedDirections(FROM.toString == option.get, TO.toString == option.get)
	}

	def fromDirection(direction: Direction): SupportedDirections =
		new SupportedDirections(FROM == direction, TO == direction)
}

@InterfaceAudience.Public
@InterfaceStability.Unstable
class SupportedDirections(var from: Boolean, var to: Boolean) extends Comparable[SupportedDirections] {
	/**
	  * Check if direction is supported.
	  *
	  * @param direction
	  * @return boolean
	  */
	def isDirectionSupported(direction: Direction): Boolean = (direction eq Direction.FROM) && from || (direction eq Direction.TO) && to

	/**
	  * @return String "FROM", "TO", "FROM/TO", "".
	  */
	override def toString: String = {
		val buffer: StringBuffer = new StringBuffer
		if (isDirectionSupported(Direction.FROM)) {
			buffer.append(Direction.FROM)
			if (isDirectionSupported(Direction.TO)) {
				buffer.append(SupportedDirections.SUPPORTED_DIRECTIONS_SEPARATOR)
				buffer.append(Direction.TO)
			}
		}
		else if (isDirectionSupported(Direction.TO)) buffer.append(Direction.TO)
		buffer.toString
	}

	def compareTo(o: SupportedDirections): Int = {
		var hash: Int = 0
		if (this.isDirectionSupported(Direction.FROM)) hash |= 1
		if (this.isDirectionSupported(Direction.TO)) hash |= 2
		var oHash: Int = 0
		if (this.isDirectionSupported(Direction.FROM)) oHash |= 1
		if (this.isDirectionSupported(Direction.TO)) oHash |= 2
		hash - oHash
	}

	override def equals(o: Any): Boolean = {
		if (this eq o.asInstanceOf[AnyRef]) return true
		if (o == null || (getClass ne o.getClass)) return false
		val that: SupportedDirections = o.asInstanceOf[SupportedDirections]
		if (from != that.from) return false
		to == that.to
	}

	override def hashCode: Int = {
		var result: Int = if (from) 1 else 0
		result = 31 * result + (if (to) 1 else 0)
		result
	}
}

