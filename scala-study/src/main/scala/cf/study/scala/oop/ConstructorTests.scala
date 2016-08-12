package cf.study.scala.oop

import org.junit.Test

/**
 * Created by fan on 2015/11/11.
 */
class ConstructorTests {

	class TheClass(what: String = "nothing", why: String = "no idea") {
		def this(str: String) = {
			this(str, "I don't know why") //has to call this
			println("this(%s)".format(str))
		}

		println("inside class ")

		{
			val valInThis = "val in default consturctor"
			println(valInThis)
			println(s"what? $what")
			println(s"why? $why")
		}

		val valInClass = "val in class"
	}

	@Test def testConstructor(): Unit = {
		new TheClass()
		new TheClass("hello")

//		val t = new TheClass()
//		println(t.valInClass)
	}
}
