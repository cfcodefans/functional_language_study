package cf.study.lang

import org.junit.Test

import scala.io.Source

/**
 * Created by fan on 2015/8/10.
 */

object LongLines {
	def processFile(filename: String, width: Int): Unit = {
		val source = Source.fromFile(filename)
		for (line <- source.getLines())
			processLine(filename, width, line)
	}

	private def processLine(filename: String, width: Int, line: String): Unit = {
		if (line.length > width)
			println("%s: %s".format(filename, line.trim))
	}
}

//def func() {
//	println(func)
//}

class FuncAndClosureTests {
	@Test
	def testFirstClassFunc(): Unit = {
		var increase = (x: Int) => x + 1
		println(increase)
		println(increase(10))
		println(increase.apply(10))

		increase = (x: Int) => {
			println("we")
			println("are")
			println("here")
			x + 1
		}

		println(increase)
		println(increase(10))
		println(increase.apply(10))
	}

	@Test
	def testLambda(): Unit = {

	}
}
