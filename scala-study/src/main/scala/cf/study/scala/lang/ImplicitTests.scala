package cf.study.scala.lang

import org.junit.Test

/**
 * Created by fan on 2016/1/20.
 */
class ImplicitTests {
	class IntWithTimes(i: Int) {
		def times[A](f: => A): Unit = {
			for (i <- 0 to i)
				f
		}
		override def toString: String = "Times: %d".format(i)
	}

	implicit def intToTimes(i: Int) = new IntWithTimes(i)

	@Test
	def testIntWithTimes: Unit = {
		5.times(println("what"))
		println(5)
		println(5.asInstanceOf[IntWithTimes].toString)
	}
}
