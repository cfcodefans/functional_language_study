package cf.study.scala.lang

import cf.study.scala.lang.CompositionAndInheritanceTests.{Element, ArrayElement}
import org.junit.Test

/**
 * Created by fan on 2015/11/3.
 */
object CompositionAndInheritanceTests {
	abstract class Element {
		def contents: Array[String]
		val height = contents.length
		val width = if (height == 0) 0 else contents(0).length
	}

	class ArrayElement(conts: Array[String]) extends Element {
		def contents: Array[String] = conts
	}
}

class CompositionAndInheritanceTests {

	@Test def testFactory(): Unit = {
//		val el:Element = new Element //can't compile, can't initiate instance of abstract class
		val ae = new ArrayElement(Array("hello", "world"))
		println(ae.width)
		println(ae)

		val e: Element = new ArrayElement(Array("hello"))
		println(e)
	}
}
