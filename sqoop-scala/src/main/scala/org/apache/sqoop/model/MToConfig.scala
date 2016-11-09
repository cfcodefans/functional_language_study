package org.apache.sqoop.model

import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}

/**
  * Config describing all required information to build the TO part of the job
  * NOTE: It extends a config list since {@link MToConfig} could consist of a related config groups
  * In future this could be simplified to hold a single list of all configs for the TO object
  */
@InterfaceAudience.Public
@InterfaceStability.Unstable
class MToConfig(configs: List[MConfig], override val mValidators: List[MValidator])
	extends MConfigList(configs, MConfigTypeEnum.JOB, mValidators) {

	override def toString: String = s"To: ${super.toString}"

	override def equals(other: Any): Boolean = {
		if (other.asInstanceOf[AnyRef] eq this) return true
		if (!other.isInstanceOf[MToConfig]) return false
		val mj: MToConfig = other.asInstanceOf[MToConfig]
		super.equals(mj)
	}

	override def hashCode: Int = super.hashCode

	override def clone(cloneWithValue: Boolean): MToConfig = new MToConfig(super.clone(cloneWithValue).getConfigs, getCloneOfValidators)
}
