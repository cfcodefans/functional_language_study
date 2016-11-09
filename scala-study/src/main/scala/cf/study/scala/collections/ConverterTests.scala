package cf.study.scala.collections

import org.junit.Test

import scala.collection.JavaConverters._
import scala.collection.mutable._

//import scala.collection.convert.DecorateAsScala

/**
  * Created by fan on 2016/10/27.
  */
class ConverterTests {
	type JMap[K, V] = java.util.Map[K, V]

	@Test
	def testImplicitConversion: Unit = {
		val jmap: JMap[String, String] = new java.util.HashMap[String, String]()

		def printMap(m: Map[String, String]) = println(m.mkString(", "))

		printMap(jmap.asScala)
	}
}
