package org.apache.sqoop.model

import java.lang.annotation.{Retention, RetentionPolicy}

import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}

import scala.annotation.StaticAnnotation

/**
  * Denote config in Configuration class
  */
@InterfaceAudience.Public
@InterfaceStability.Evolving
@Retention(RetentionPolicy.RUNTIME)
case class Config(
	                 /**
	                   * Optional name for the config object
	                   *
	                   * @return
	                   */
	                 name: String = ""
                 ) extends StaticAnnotation {

}
