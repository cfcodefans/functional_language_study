package org.apache.sqoop.job

import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}

@InterfaceAudience.Private
@InterfaceStability.Unstable
trait Constants {
	/**
	  * All job related configuration is prefixed with this:
	  * <tt>org.apache.sqoop.job.</tt>
	  */
	val PREFIX_CONFIG: String = "org.apache.sqoop.job."
	val JOB_ETL_NUMBER_PARTITIONS: String = PREFIX_CONFIG + "etl.number.partitions"
	val JOB_ETL_FIELD_NAMES: String = PREFIX_CONFIG + "etl.field.names"
	val JOB_ETL_OUTPUT_DIRECTORY: String = PREFIX_CONFIG + "etl.output.directory"
	val JOB_ETL_INPUT_DIRECTORY: String = PREFIX_CONFIG + "etl.input.directory"
}