package cf.study.scala.util.control

import org.junit.Test

import scala.util.control.Exception.Catch
import scala.util.control._

/**
  * Created by fan on 2016/10/12.
  */
class ExceptionTests {

	@Test def testOpt: Unit = {
		val result: Int = Exception.catching(classOf[NumberFormatException])
			.opt(Integer.parseInt("abc"))
			.getOrElse(Integer.MIN_VALUE)
		println(result)
	}

	def f(): Int = {
		println("in supplied body")
		Integer.parseInt("abc")
	}

	@Test def testCatching: Unit = {
		val c: Catch[Int] = Exception.catching(classOf[NumberFormatException])

		val vf: () => Int = () => {
			println("in supplied body")
			Integer.parseInt("abc")
		}

		//default behavior is to rethrow the exception
		println(c.apply[Int](f))
	}

	@Test def testCatcher(): Unit = {
		val pf: PartialFunction[Throwable, Int] = {
			case t: Throwable => {
				println(t)
				Int.MinValue
			}
		}
		val c: Catch[Int] = Exception.catching(pf)

		val re: Int = c({
			println("in supplied body")
			Integer.parseInt("abc")
		})
		println(re)
	}

	@Test def testBreak: Unit = {
		Breaks.breakable({
			for (i <- 1.to(100)) {
				if (i == 50) {
					println(s"break at $i")
					Breaks.break()
				}
			}
		})

		Breaks.breakable({
			1 to 100 foreach(i => {
				if (i == 50) {
					println(s"break at $i")
					Breaks.break()
				}
			})
		})
	}
}
