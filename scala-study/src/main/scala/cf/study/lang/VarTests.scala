package cf.study.lang

import org.junit.Test
/**
 * Created by Administrator on 2015/6/21.
 */
class VarTests {
	@Test def defineSomeVariables(): Unit = {
		val msg = "Hello, world!"
		println(msg)

		val msg2: java.lang.String = "Hello again, world!"
		println(msg2)

		val msg3: String = "Hello yet again, world"
		println(msg3)

//      error: reassignment to val
//		msg = "Goodbye cruel world!world"

		var greeting = "Hello, world!"
		println(greeting)
		greeting = "Leave me aline, world!"
		println(greeting)
	}

	@Test def defineSomeFunctions(): Unit = {
		def max(x: Int, y: Int): Int={
			if (x > y) x
			else y
		}
		def max2(x: Int, y: Int) = if (x > y) x else y

		println(max(3, 5))
		println(max2(3, 5))

		def greet() = println("Hello, world!")
		greet()
	}

	@Test def loopWithWhile(): Unit = {
		val args:Array[String] = Array[String]("loog", "with", "while")

		var i = 0;
		while (i < args.length) {
			println(args(i))
			i += 1
		}

		i = 0
		while (i < args.length) {
			if (i != 0)
				print(" ")
			print(args(i))
			i += 1
		}
		println()
	}

	@Test def iterateWithForeachAndFor(): Unit = {
		val args:Array[String] = Array[String]("loog", "with", "while")

		args.foreach(arg => println(arg))
		println()
		args.foreach(println)
		println()

		for (arg <- args) {
			print(arg)
//			arg is a val, can't reassign a new value to val
//			arg = ","
		}

	}
}
