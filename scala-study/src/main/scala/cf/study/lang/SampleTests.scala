package cf.study.lang

import java.util

import org.junit.Test

/**
 * Created by fan on 2015/6/4.
 */
class SampleTests {
	@Test def testFoo(): Unit = {
		print("Hello Scala!")
	}

	@Test def testMapLiteral(): Unit = {
		var capital = Map("US" -> "Washington", "France" -> "Paris")
		println(capital)
		println(capital("France"))
		capital += ("Japan" -> "Tokyo")
		println(capital)
		capital -= "US"
		println(capital)
		capital -= "China"
		println(capital)
	}

	@Test def testBigInt(): Unit = {
		def factorial(x: BigInt): BigInt = {
			if (x == 0) 1 else x * factorial(x - 1)
		}

		println(factorial(20))
	}

	class MyClass(index: Int, name: String) {
		override def toString(): String = {
			"{index: " + this.index + ", name: '" + this.name + "'}"
		}
	}
	@Test def testClass(): Unit = {
		val myObj = new MyClass(1, "Obj")
		println(myObj)
	}

	@Test def testHighLevelFunc(): Unit = {
		val string = "abc"
		println(string.exists(_.isUpper))
	}

	@Test def testTrait(): Unit = {

	}

	@Test def testJavaLib(): Unit = {
//		val map : HashMap[Int, String] = new HashMap[Int, String]()
//		println(map)
	}

	@Test def testVars(): Unit = {
		val msg = "Hello, world!"
		println(msg)

		val msg2: java.lang.String = "Hello again, world!"
		println(msg2)

		val msg3: String = "Hello yet again, world"
		println(msg3)

//		you can't reassign a val, but you can reassign a var
//		msg = "Goodby cruel world!"
		println(msg)

		var greeting = "Hello, world"
		println(greeting)

		greeting = "Leave me alone, world"
		println(greeting)

		val multiLine = "This is line one"
	}

	def max(x: Int, y: Int): Int = {
		if (x > y) x
		else y
	}

	def _max(x: Int, y: Int) = if (x > y) x else y

	@Test def testFunction(): Unit = {
		println(max(1, 1))
		println(max(1, 2))
		println(max(2, 1))

		println(_max(1, 1))
		println(_max(1, 2))
		println(_max(2, 1))
	}

	@Test def testUnitType(): Unit = {
		def greet() = println("function greet")
		println(greet())
	}

	@Test def testWhileLoop(): Unit = {
		var i:Int = 0
		while (i < 10) {
			println(i)
			i += 1
		}
	}

	@Test def testForLoop(): Unit = {
		val array = Array("What", "is", "scala", "array", "like")
		array.foreach(println)
		array.foreach(e => println(e))
	}

	@Test def testParameterized(): Unit = {
		val greetStrings = new Array[String](3)
		//val greetString: Array[String] = new Array[String](3)
		greetStrings(0) = "Hello"
		greetStrings(1) = ", "
		greetStrings(2) = "World!\n"
		for (i <- 0 to 2)
			print(greetStrings(i))
	}


}
