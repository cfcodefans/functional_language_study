package cf.study.scala.lang

import java.lang.annotation.Documented

import org.junit.Test

import scala.annotation.ClassfileAnnotation
import scala.beans.{BeanDescription, BeanDisplayName}
import scala.reflect.runtime.universe._

class TestAnno(val v: String) extends ClassfileAnnotation {

}

class AnnotationTests {

    @Test def testDefaultAnno: Unit = {
        @BeanDescription("test-class")
        @BeanDisplayName("test")
        @TestAnno(v = "what")
        @Documented
        class Dummy {

        }

        classOf[Dummy].getAnnotations.foreach(System.out.println(_))
        classOf[Dummy].getDeclaredAnnotations.foreach(System.out.println(_))

        val d: Dummy = new Dummy

        d.getClass.getAnnotations.foreach(System.out.println(_))
        d.getClass.getDeclaredAnnotations.foreach(System.out.println(_))
    }

    @Test def testDefaultAnno1: Unit = {
        @BeanDescription("test-class")
        @BeanDisplayName("test")
        @TestAnno(v = "what")
        @Documented
        case class Dummy()

        symbolOf[Dummy].asClass.annotations.foreach(println)
    }
}
