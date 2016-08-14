package org.apache.sqoop.model

import java.lang.annotation.{ElementType, Retention, RetentionPolicy, Target}

import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}

import scala.annotation.StaticAnnotation

/**
  * Denote configuration class
  */
@InterfaceAudience.Public
@InterfaceStability.Evolving
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
case class ConfigClass(
	                      /**
	                        * Default size for Inputs in this config.
	                        *
	                        * @return
	                        */
	                      defaultSize: Short = -1,

	                      /**
	                        * List of validators associated with this config.
	                        *
	                        * @return
	                        */
	                      validators: Array[Validator] = Array.empty[Validator]
                      ) extends StaticAnnotation {

}
