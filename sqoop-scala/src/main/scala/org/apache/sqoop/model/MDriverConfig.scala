package org.apache.sqoop.model

import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}

/**
  * Config describing all required information for the driver
  * NOTE: It extends a config list since {@link MToConfig} could consist of a related config groups
  * In future this could be simplified to hold a single list of all configs for the driver
  */
@InterfaceAudience.Public
@InterfaceStability.Unstable
class MDriverConfig(configs: List[MConfig], override val mValidators: List[MValidator]) extends MConfigList(configs, MConfigTypeEnum.JOB, mValidators) {

	override def toString: String = s"Driver: ${super.toString}"

	override def equals(other: Any): Boolean = {
		if (other.asInstanceOf[AnyRef] eq this) return true
		if (!other.isInstanceOf[MDriverConfig]) return false
		val mDriver: MDriverConfig = other.asInstanceOf[MDriverConfig]
		super.equals(mDriver)
	}

	override def hashCode: Int = super.hashCode

	override def clone(cloneWithValue: Boolean): MDriverConfig =  new MDriverConfig(super.clone(cloneWithValue).getConfigs, getCloneOfValidators)
}