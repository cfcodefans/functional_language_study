package cf.study.scala.lang

import cf.study.scala.util.ClassLens
import org.junit.Test

/**
  * Created by fan on 2016/8/16.
  */
class AnyTests {
	@Test def testAny1: Unit = {
		val any: Any = 1
		val anyRef: AnyRef = any.asInstanceOf[AnyRef]

		println(any == anyRef)
		println(any.equals(anyRef))
		println(any.asInstanceOf[AnyRef].eq(anyRef))
		println(anyRef.eq(any.asInstanceOf[AnyRef]))
	}

	@Test def testAny2: Unit = {
		val any: Any = "1"
		val anyRef: AnyRef = new String("1").asInstanceOf[AnyRef]

		println(any == anyRef)
		println(any.equals(anyRef))
		println(any.asInstanceOf[AnyRef].eq(anyRef))
		println(anyRef.eq(any.asInstanceOf[AnyRef]))
	}

	@Test def testAnyRefCls: Unit = {
		val any: Any = new Object
		val anyRef: AnyRef = new AnyRef

		println(ClassLens.prespective(any.getClass))
		println(ClassLens.prespective(anyRef.getClass))
	}

	@Test def testAnyRefCls1: Unit = {
		println(ClassLens.prespective(classOf[Any]))
		println(ClassLens.prespective(classOf[AnyRef]))
	}
}
