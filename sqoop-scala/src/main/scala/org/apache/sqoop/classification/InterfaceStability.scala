package org.apache.sqoop.classification

import java.lang.annotation.{Documented, Retention, RetentionPolicy}

import scala.annotation.StaticAnnotation

/**
  * Created by fan on 2016/8/11.
  */
@InterfaceAudience.Public
@InterfaceStability.Evolving
object InterfaceStability {
	@Documented
	@Retention(RetentionPolicy.RUNTIME)
	case class Stable() extends StaticAnnotation

	@Documented
	@Retention(RetentionPolicy.RUNTIME)
	case class Evolving() extends StaticAnnotation

	@Documented
	@Retention(RetentionPolicy.RUNTIME)
	case class Unstable() extends StaticAnnotation
}
