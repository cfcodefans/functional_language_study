package org.apache.sqoop.model

import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}
import org.apache.sqoop.common.SqoopException
import org.apache.sqoop.model.MConfigTypeEnum.MConfigType

@InterfaceAudience.Public
@InterfaceStability.Unstable
class MConfigList(val configObjects: List[MConfig],
                  val _type: MConfigType,
                  val mValidators: List[MValidator]) extends MValidatedElement(mValidators) with MClonable {

	def getConfigs: List[MConfig] = configObjects

	def getConfig(configName: String): MConfig = {
		val headOption: Option[MConfig] = configObjects.find(_.getName == configName)
		if (headOption.isEmpty)
			throw new SqoopException(ModelError.MODEL_010, "config name: " + configName)
		return headOption.get
	}

	def getType: MConfigType = _type

	def getInput(name: String): MInput[_] = {
		val parts: Array[String] = name.split("\\.")
		if (parts.length != 2) throw new SqoopException(ModelError.MODEL_009, name)
		getConfig(parts(0)).getInput(name)
	}

	def getStringInput(name: String): MStringInput = getInput(name).asInstanceOf[MStringInput]

	def getEnumInput(name: String): MEnumInput = getInput(name).asInstanceOf[MEnumInput]

	def getIntegerInput(name: String): MIntegerInput = getInput(name).asInstanceOf[MIntegerInput]

	def getLongInput(name: String): MLongInput = getInput(name).asInstanceOf[MLongInput]

	def getMapInput(name: String): MMapInput = getInput(name).asInstanceOf[MMapInput]

	def getBooleanInput(name: String): MBooleanInput = getInput(name).asInstanceOf[MBooleanInput]

	def getListInput(name: String): MListInput = getInput(name).asInstanceOf[MListInput]

	def getDateTimeInput(name: String): MDateTimeInput = getInput(name).asInstanceOf[MDateTimeInput]

	override def equals(o: Any): Boolean = {
		if (this.asInstanceOf[AnyRef].eq(o.asInstanceOf[AnyRef])) return true
		if (!o.isInstanceOf[MConfigList]) return false
		val mConfigList: MConfigList = o.asInstanceOf[MConfigList]
		if (configObjects != mConfigList.configObjects || _type != mConfigList._type) return false
		true
	}

	override def hashCode: Int = {
		var result: Int = super.hashCode
		result = 31 * result + _type.hashCode
		for (config <- configObjects) {
			result = 31 * result + config.hashCode
		}
		result
	}

	override def toString: String = configObjects.mkString("Configs: ", "", s"Type: ${_type}")

	def clone(cloneWithValue: Boolean): MConfigList = {
		var copyConfigs: List[MConfig] = null
		if (this.getConfigs != null)
			copyConfigs = getConfigs.map(cfg => {
				val _cfg = cfg.clone(true)
				_cfg.setPersistenceId(cfg.getPersistenceId)
				_cfg
			})

		val copyConfigList: MConfigList = new MConfigList(copyConfigs, _type, getCloneOfValidators)
		copyConfigList
	}
}
