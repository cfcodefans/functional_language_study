package cf.study.scala.lang

import cf.study.scala.lang.CompositionAndInheritanceTests.{Tiger, Element, ArrayElement}
import org.junit.Test

/**
 * Created by fan on 2015/11/3.
 */
object CompositionAndInheritanceTests {
	abstract class Element {
		def contents: Array[String] //this is an abstract method
		val height = contents.length // this is val
		val width = if (height == 0) 0 else contents(0).length
	}

	abstract class _Element {
		def contents: Array[String] //this is an abstract method
		def height: Int = contents.length //this is a method
		def width: Int = if (height == 0) 0 else contents(0).length
	}

	/* parametric field with val, if there is not val, it isn't a field */
	class ArrayElement(val conts: Array[String] ) extends Element {
		val contents: Array[String] = conts //Overriding a parameterless method with a field
	}

	/*	class CompilesFine {
		private int f = 0;
		public int f() { return 1;}
	}	 */
	/*	class WontCompile {
		private var f = 0 //won't compile, because a field
		def f = 1 // and method have the same name
	}*/

	class LineElement(s: String) extends ArrayElement(Array(s)) {

	}

	class Cat {
		val dangerous = false
	}

	class Tiger(param1: Boolean, param2: Int) extends Cat {
		override val dangerous = param1 //has to add override modifier
		private var age = param2
	}
}

class CompositionAndInheritanceTests {

	@Test def testFactory(): Unit = {
//		val el:Element = new Element //can't compile, can't initiate instance of abstract class
		val ae = new ArrayElement(Array("hello", "world"))
		println(ae.width)
		println(ae)
		println(ae.conts)

		val e: Element = new ArrayElement(Array("hello"))
		println(e)

		val t = new Tiger(true, 10)
	}
}
