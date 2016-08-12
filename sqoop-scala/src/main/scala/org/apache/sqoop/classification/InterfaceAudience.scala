package org.apache.sqoop.classification

import java.lang.annotation.{Documented, Retention, RetentionPolicy}

import scala.annotation.StaticAnnotation

/**
  * Created by fan on 2016/8/11.
  */
@InterfaceAudience.Public
@InterfaceStability.Evolving
object InterfaceAudience {

	@Documented
	@Retention(RetentionPolicy.RUNTIME)
	case class Public(value: Array[String] = Array.empty[String]) extends StaticAnnotation

	@Documented
	@Retention(RetentionPolicy.RUNTIME)
	case class LimitedPrivate(value: Array[String] = Array.empty[String]) extends StaticAnnotation

	@Documented
	@Retention(RetentionPolicy.RUNTIME)
	case class Private() extends StaticAnnotation

}
