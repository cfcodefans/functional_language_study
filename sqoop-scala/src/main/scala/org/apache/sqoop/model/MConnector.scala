package org.apache.sqoop.model

import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}
import org.apache.sqoop.model.MConfigurableType.MConfigurableType

/**
  * Connector entity supports the FROM/TO {@link org.apache.sqoop.job.etl.Transfereable} Includes unique id
  * that identifies connector in the repository, unique human readable name,
  * corresponding name and all configs to support the from and to data sources
  */
@InterfaceAudience.Public
@InterfaceStability.Unstable
class MConnector(val uniqueName: String,
                 val className: String,
                 val version: String,
                 val linkConfig: MLinkConfig,
                 val fromConfig: MFromConfig,
                 val toConfig: MToConfig
                ) extends Configurable {
	//TODO
	if (uniqueName == null || className == null) throw new NullPointerException

	def getUniqueName: String = uniqueName

	def getClassName: String = className

	def getLinkConfig: MLinkConfig = linkConfig

	def getFromConfig: MFromConfig = fromConfig

	def getToConfig: MToConfig = toConfig

	def getVersion: String = version

	def getType: MConfigurableType = MConfigurableType.CONNECTOR

	def clone(cloneWithValue: Boolean): MConnector = {
		// Connector never have any values filled
		val isCloneWithValue: Boolean = false
		var fromConfig: MFromConfig = this.getFromConfig
		var toConfig: MToConfig = this.getToConfig
		if (fromConfig != null) fromConfig = fromConfig.clone(isCloneWithValue)
		if (toConfig != null) toConfig = toConfig.clone(isCloneWithValue)
		val copy: MConnector = new MConnector(this.getUniqueName, this.getClassName, this.getVersion, this.getLinkConfig.clone(isCloneWithValue), fromConfig, toConfig)
		copy.setPersistenceId(this.getPersistenceId)
		copy
	}

	def getSupportedDirections: SupportedDirections = new SupportedDirections(this.getFromConfig != null, this.getToConfig != null)

	override def hashCode: Int = {
		val supportedDirections: SupportedDirections = getSupportedDirections
		var result: Int = getLinkConfig.hashCode
		if (supportedDirections.isDirectionSupported(Direction.FROM)) result = 31 * result + getFromConfig.hashCode
		if (supportedDirections.isDirectionSupported(Direction.TO)) result = 31 * result + getToConfig.hashCode
		result = 31 * result + version.hashCode
		result = 31 * result + uniqueName.hashCode
		result = 31 * result + className.hashCode
		result
	}

	override def equals(other: Any): Boolean = {
		if (other.asInstanceOf[AnyRef] eq this) return true
		if (!other.isInstanceOf[MConnector]) return false
		val mc: MConnector = other.asInstanceOf[MConnector]
		val supportedDirections: SupportedDirections = this.getSupportedDirections
		val mcSupportedDirections: SupportedDirections = mc.getSupportedDirections

		if (supportedDirections.isDirectionSupported(Direction.FROM)
			&& mcSupportedDirections.isDirectionSupported(Direction.FROM)
			&& getFromConfig != mc.getFromConfig)
			return false

		if (supportedDirections.isDirectionSupported(Direction.FROM) != mcSupportedDirections.isDirectionSupported(Direction.FROM))
			return false

		if (supportedDirections.isDirectionSupported(Direction.TO)
			&& mcSupportedDirections.isDirectionSupported(Direction.TO)
			&& getToConfig != mc.getToConfig)
			return false

		if (supportedDirections.isDirectionSupported(Direction.TO) != mcSupportedDirections.isDirectionSupported(Direction.TO))
			return false

		uniqueName == mc.uniqueName && className == mc.className && version == mc.version && linkConfig == mc.getLinkConfig
	}
}
