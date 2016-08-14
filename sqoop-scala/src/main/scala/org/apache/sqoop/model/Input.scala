package org.apache.sqoop.model

import java.lang.annotation.{ElementType, Retention, RetentionPolicy, Target}

import org.apache.commons.lang3.StringUtils
import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}
import org.apache.sqoop.model.InputEditable.InputEditable

import scala.annotation.StaticAnnotation

/**
  * Field annotation. Each field that user might change in configuration object
  * need to have this annotation.
  */
@InterfaceAudience.Public
@InterfaceStability.Evolving
@Retention(RetentionPolicy.RUNTIME)
@Target(Array(ElementType.FIELD))
case class Input(
	                /**
	                  * Sqoop will ensure that sensitive information will not be easily
	                  * accessible.
	                  *
	                  * @return True if field is sensitive
	                  */
	                sensitive: Boolean = false,

	                /**
	                  * If this input is a map, keys matching this regular expression will be
	                  * treated as sensitive. Sqoop will ensure that values associated with the
	                  * sensitive keys will not be easily accessible.
	                  *
	                  * @return The regular expression that matches sensitive fields
	                  */
	                sensitiveKeyPattern: String = StringUtils.EMPTY,

	                /**
	                  * Indicates the entity that can edit the input's values, all inputs are
	                  * created/deleted only by the connector code, other entities do not have
	                  * access to either create/delete an input
	                  *
	                  * @return editable
	                  */
	                editable: InputEditable = InputEditable.ANY,

	                /**
	                  * Maximal length of field if applicable.
	                  *
	                  * @return Maximal length
	                  */
	                size: Short = -1,

	                /**
	                  * In-order to express dependency on other inputs, the value supports a comma
	                  * separated list of other inputs in the config class. It validates the
	                  * attribute value obeys the expected conditions
	                  */
	                overrides: String = StringUtils.EMPTY,

	                /**
	                  * List of validators associated with this input.
	                  *
	                  * @return
	                  */
	                validators: Array[Validator] = Array.empty
                ) extends StaticAnnotation {

}
