package cf.study.scala.oop

import org.junit.Test

/**
 * Created by fan on 2015/11/11.
 */
class ConstructorTests {

	class TheClass {
		def this(str: String) = {
			this //has to call this
			println("this(%s)".format(str))
		}

		println("this()")
	}

	@Test def testConstructor(): Unit = {
		new TheClass()
		new TheClass("hello")
	}
}
