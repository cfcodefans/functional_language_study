package org.apache.sqoop.model

import java.lang.annotation.{Retention, RetentionPolicy}

import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}
import org.apache.sqoop.validation.validators.AbstractValidator

import scala.annotation.StaticAnnotation

/**
  * Annotation for specifying validators
  *
  * Usage without any parameters:
  *
  * Validator (ClassName.class)
  *
  * To specify string parameter call:
  * Validator (value = ClassName.class, strArg = "Hello World!")
  */
@InterfaceAudience.Public
@InterfaceStability.Unstable
@Retention(RetentionPolicy.RUNTIME)
case class Validator(/**
                       * Validator implementation that should be executed.
                       */
                     value: Class[AbstractValidator[_]],
                     /**
                       * Optional argument that should be given to the validator before execution.
                       */
                     strArg: String = AbstractValidator.DEFAULT_STRING_ARGUMENT
                    ) extends StaticAnnotation {

}
