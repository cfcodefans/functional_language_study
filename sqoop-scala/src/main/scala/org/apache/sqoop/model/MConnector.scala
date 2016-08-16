package org.apache.sqoop.model

import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}

/**
  * Connector entity supports the FROM/TO {@link org.apache.sqoop.job.etl.Transfereable} Includes unique id
  * that identifies connector in the repository, unique human readable name,
  * corresponding name and all configs to support the from and to data sources
  */
@InterfaceAudience.Public
@InterfaceStability.Unstable
class MConnector extends Configurable {
	//TODO
}
