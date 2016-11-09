package org.apache.sqoop.job.etl

import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}

/**
  * This allows connector to define initialization work for execution,
  * for example, context configuration.
  *
  * All method invocations on an instance of Initializer can be assumed
  * to come from the same process.
  */
@InterfaceAudience.Public
@InterfaceStability.Evolving
abstract class Initializer[LinkConfiguration, JobConfiguration] {
	/**
	  * Initialize new submission based on given configuration properties. Any
	  * needed temporary values might be saved to context object and they will be
	  * promoted to all other part of the workflow automatically.
	  *
	  * @param context           Initializer context object
	  * @param linkConfiguration link configuration object
	  * @param jobConfiguration  job configuration object for the FROM and TO
	  *                          In case of the FROM initializer this will represent the FROM job configuration
	  *                          In case of the TO initializer this will represent the TO job configuration
	  */
	def initialize(context: InitializerContext, linkConfiguration: LinkConfiguration, jobConfiguration: JobConfiguration)

	/**
	  * Return list of all jars that this particular connector needs to operate on
	  * following job. This method will be called after running initialize method.
	  *
	  * @param context           Initializer context object
	  * @param linkConfiguration link configuration object
	  * @param jobConfiguration  job configuration object for the FROM and TO
	  *                          In case of the FROM initializer this will represent the FROM job configuration
	  *                          In case of the TO initializer this will represent the TO job configuration
	  * @return
	  */
	def getJars(context: InitializerContext, linkConfiguration: LinkConfiguration, jobConfiguration: JobConfiguration): util.Set[String] = new util.HashSet[String]

	/**
	  * Return schema associated with the connector for FROM and TO
	  * By default we assume a null schema. Override the method if there a custom schema to provide either for FROM or TO
	  *
	  * @param context           Initializer context object
	  * @param linkConfiguration link configuration object
	  * @param jobConfiguration  job configuration object for the FROM and TO
	  *                          In case of the FROM initializer this will represent the FROM job configuration
	  *                          In case of the TO initializer this will represent the TO job configuration
	  * @return
	  */
	def getSchema(context: InitializerContext, linkConfiguration: LinkConfiguration, jobConfiguration: JobConfiguration): Schema = NullSchema.getInstance
}
