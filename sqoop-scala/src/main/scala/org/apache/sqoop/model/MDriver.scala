package org.apache.sqoop.model

import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}
import org.apache.sqoop.model.MConfigurableType.MConfigurableType

import scala.beans.BeanProperty

/**
  * Describes the configs associated with the {@link Driver} for executing sqoop jobs.
  */
@InterfaceAudience.Public
@InterfaceStability.Unstable
class MDriver(_driverConfig: MDriverConfig, _version: String) extends Configurable {
	val DRIVER_NAME: String = "SqoopDriver"
	private val driverConfig: MDriverConfig = _driverConfig
	@BeanProperty var version: String = _version
	// Since there is only one Driver in the system, the name is not user specified
	private val uniqueName: String = DRIVER_NAME

	override def toString: String = s"driver-${getPersistenceId}:version = ${version}, ${driverConfig}"

	override def equals(other: Any): Boolean = {
		if (other.asInstanceOf[AnyRef] eq this) return true
		if (!other.isInstanceOf[MDriver]) return false
		val driver: MDriver = other.asInstanceOf[MDriver]
		version == driver.getVersion && driverConfig == driver.driverConfig
	}

	override def hashCode: Int = driverConfig.hashCode * 31 + {if (version != null) version.hashCode else 0}

	def getDriverConfig: MDriverConfig = driverConfig

	def getType: MConfigurableType = MConfigurableType.DRIVER

	def getUniqueName: String = uniqueName

	def clone(cloneWithValue: Boolean): MDriver = {
		val copy: MDriver = new MDriver(this.driverConfig.clone(false), this.version)
		copy.setPersistenceId(this.getPersistenceId)
		copy
	}

}
