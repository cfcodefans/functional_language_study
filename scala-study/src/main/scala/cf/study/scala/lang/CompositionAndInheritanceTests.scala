package cf.study.scala.lang

import cf.study.scala.lang.CompositionAndInheritanceTests._
import org.junit.Test

/**
 * Created by fan on 2015/11/3.
 */
object CompositionAndInheritanceTests {
	abstract class Element {
		def contents(): Array[String] //this is an abstract method
		val height = contents().length // this is val
		val width = if (height == 0) 0 else contents()(0).length

		def demo(): Unit = {
			println("Element's implementation invoked")
		}
	}

	abstract class _Element {
		def contents: Array[String] //this is an abstract method
		def height: Int = contents.length //this is a method
		def width: Int = if (height == 0) 0 else contents(0).length
	}

	/* parametric field with val, if there is not val, it isn't a field */
	class ArrayElement(val conts: Array[String] ) extends Element {
		override val contents: Array[String] = conts //Overriding a parameterless method with a field
		override def demo(): Unit = {
			println("ArrayElement's implementation invoked")
		}
	}

	/*	class CompilesFine {
		private int f = 0;
		public int f() { return 1;}
	}	 */
	/*	class WontCompile {
		private var f = 0 //won't compile, because a field
		def f = 1 // and method have the same name
	}*/
	//ArrayElement(Array(s)) calling constructor of super
	class LineElement(s: String) extends ArrayElement(Array(s)) {
//		override def width = s.length
//		override def height = 1
	}

	class Cat {
		val dangerous = false
	}

	class Tiger(param1: Boolean, param2: Int) extends Cat {
		override val dangerous = param1 //has to add override modifier
		private var age = param2
	}

	class _Tiger(override val dangerous: Boolean, private var age: Int) extends Cat

	class UniformElement(ch: Char,
	                     override val width: Int,
	                     override val height: Int) extends Element {
		private val line = ch.toString * width
		def contents = Array.fill(height)(line)
	}

	def printClass[T](cls: Class[T]): Unit = {
		if (cls == null) return
		cls.getDeclaredFields.foreach(f => println(f))
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
		t.dangerous
//		t.age //private val is invisible
		val _t = new _Tiger(true, 10)
		_t.dangerous
//		_t.age //private field is invisible
	}

	@Test def testHierarchy(): Unit = {
		val e1: Element = new ArrayElement(Array("Hello", "world"))
		val ae: ArrayElement = new LineElement("Hello")
		val e2: Element = ae
		val e3: Element = new UniformElement('x', 2, 3)

		e1.demo()
		ae.demo()
		e2.demo()
		e3.demo()
	}

	@Test def testValAndMethods(): Unit = {
		{
			abstract class Abstract {
				def foo
			}

			class Concrete extends Abstract {
				def foo = println("foo")
			}
//			val abs = new Abstract //abstract class can't be initiated
			val abs: Abstract = new Concrete
			abs.foo
		}

		{
			class Bar(id: Int) {
				override def toString: String = "%s_%d".format(super.toString, id)
			}
			val bar:Bar = new Bar(10)
			println(bar)
			printClass(classOf[Bar])
//			bar.id //id is private
			println()
		}

		{
			class Bar(val id: Int) {
				override def toString: String = "%s_%d".format(super.toString, id)
			}
			val bar:Bar = new Bar(11)
			println(bar)
			println(bar.id) // id becomes val
			printClass(classOf[Bar])
			println()
		}

		{
			class Bar(id: Int) {
				val barId:Int = id;
				override def toString: String = "%s_%d".format(super.toString, id)
			}
			val bar:Bar = new Bar(12)
			println(bar)
			println(bar.barId) //_id is an Pattern?
			//			bar.id //id is private
			printClass(classOf[Bar])
			println()
		}

		{
			class Bar(val id: Int) {
				val barId:Int = id;
				override def toString: String = "%s_%d".format(super.toString, id)
			}
			val bar:Bar = new Bar(13)
			println(bar)
			println(bar.barId) //_id is an Pattern?
			println(bar.id) // id becomes val
			//			bar.id //id is private
			printClass(classOf[Bar])
			println()
		}
	}

	@Test def testType(): Unit = {
//		println(classOf[])
	}
}
