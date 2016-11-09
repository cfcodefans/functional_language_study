package org.apache.sqoop.model

import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}

/**
  * Config describing all required information to build the FROM part of the job
  * NOTE: It extends a config list since {@link MFromConfig} could consist of a related config groups
  * In future this could be simplified to hold a single list of all configs for the FROM object
  */
@InterfaceAudience.Public
@InterfaceStability.Unstable
class MFromConfig(val configs: List[MConfig], override val mValidators: List[MValidator])
	extends MConfigList(configs, MConfigTypeEnum.JOB, mValidators) {

	override def toString: String = s"From: ${super.toString}"

	override def equals(other: Any): Boolean = {
		if (other.asInstanceOf[AnyRef] eq this) return true
		if (!other.isInstanceOf[MFromConfig]) return false
		val mj: MFromConfig = other.asInstanceOf[MFromConfig]
		return super.equals(mj)
	}

	override def hashCode: Int = super.hashCode

	override def clone(cloneWithValue: Boolean): MFromConfig =
		new MFromConfig(super.clone(cloneWithValue).getConfigs, getCloneOfValidators)
}
