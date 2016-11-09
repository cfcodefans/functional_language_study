package cf.study.scala.lang

import cf.study.scala.util.ClassLens
import org.junit.Test

/**
  * Created by fan on 2016/10/24.
  */
trait TConfig[VT <: java.io.Serializable] extends (String, String) with java.io.Serializable {
	def key: String = _1

	def value: VT
}

class GenericTypeTests {
	@Test
	def testTypeUpperBoundary: Unit = {
		val n: Int = 0
		println(ClassLens.prespective(classOf[TConfig[_]]))
		class IntCfg(k: String, v: String) extends (String, String)(k, v) with TConfig[Integer] {
			override def value: Integer = _2.toInt
		}
		println(ClassLens.prespective(classOf[IntCfg]))
	}

	@Test
	def testTypeAndImplicit: Unit = {
		class BoolCfg(k: String, v: String) extends (String, String)(k, v) with TConfig[java.lang.Boolean] {
			override def value: java.lang.Boolean = java.lang.Boolean.valueOf(_2)
		}


	}
}
