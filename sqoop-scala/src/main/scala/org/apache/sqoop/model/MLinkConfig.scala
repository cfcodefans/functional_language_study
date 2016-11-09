package org.apache.sqoop.model

import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}

/**
  * Config describing all required information to build up an link object
  * NOTE: It extends a config list since {@link MLink} could consist of a related config groups
  * In future this could be simplified to hold a single list of all configs for the link object
  */
@InterfaceAudience.Public
@InterfaceStability.Unstable
class MLinkConfig(configs: List[MConfig],
                  override val mValidators: List[MValidator])
	extends MConfigList(configs, MConfigTypeEnum.LINK, mValidators) {

	override def toString: String = s"Link: ${super.toString}"

	override def clone(cloneWithValue: Boolean): MLinkConfig =
		new MLinkConfig(super.clone(cloneWithValue).getConfigs, getCloneOfValidators)
}
