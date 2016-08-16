package org.apache.sqoop.model

import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}

/**
  * Represents an element of metadata used by the connector.
  */
@InterfaceAudience.Private
@InterfaceStability.Unstable
object MNamedElement {
	private val LABEL_KEY_SUFFIX: String = ".label"
	private val HELP_KEY_SUFFIX: String = ".help"
}

@InterfaceAudience.Private
@InterfaceStability.Unstable
abstract class MNamedElement protected(val _name: String, val mValidators: List[MValidator]) extends MValidatedElement(mValidators) {
	//	setName(name)
	private var name: String = _name
	private var labelKey: String = null
	private var helpKey: String = null

	def this(other: MNamedElement) {
		this(other.getName, other.getCloneOfValidators)
	}

	/**
	  * @return the name of this parameter
	  */
	def getName: String = name

	/**
	  * Set new name for this entity.
	  *
	  * @param name
	  */
	def setName(name: String) {
		this.name = name
		labelKey = name + MNamedElement.LABEL_KEY_SUFFIX
		helpKey = name + MNamedElement.HELP_KEY_SUFFIX
	}

	/**
	  * @return the label key to be used for this parameter
	  */
	def getLabelKey: String = labelKey

	/**
	  * @return the help key to be used for this parameter
	  */
	def getHelpKey: String = helpKey

	def toString: String
}
