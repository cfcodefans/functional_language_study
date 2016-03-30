package cf.study.scala.lang

import java.util.Date

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

	@Test
	def testImplicitFuncAndValue: Unit = {
		class DateLong(_date: Date) {
			val longVal:Long = _date.getTime
			val date:Date = _date

			def this(_long:Long) = {
				this(new Date(_long))
			}

			override def toString():String = s"$date\t=\t$longVal"
		}

		def printDateLong(dl: DateLong) = println(dl)
		val _l:Long = 0
		printDateLong(new DateLong(_l))

		implicit def longToDateLong(_l:Long) = new DateLong(_l)
		printDateLong(_l)

		implicit val theMoment:Long = 10000000;
		def when(implicit _l:Long) = new DateLong(_l)
		println(when)
	}

	@Test
	def testImplicitClass: Unit = {
		implicit class TriangleFunc(_d:Double) {
			def sin = Math.sin(_d)
			def cos = Math.cos(_d)
		}

		println(3.14, 3.14.sin)
		println(1.57, 1.57.sin)
	}

	@Test
	def testImplicitObjectAndArg: Unit = {
		trait Middle[T] {
			def middle(a:T, b:T): T
		}
		implicit object MiddleInt extends Middle[Int] {
			def middle(a:Int, b:Int) = (a + b) / 2
		}
		implicit object MiddleString extends Middle[String] {
			def middle(a:String, b:String) = a.substring(a.length / 2, a.length) + b.substring(0, b.length / 2)
		}
		implicit object MiddleDate extends Middle[Date] {
			def middle(d1:Date, d2:Date) = new Date({if (d1.before(d2)) d1 else d2}.getTime + Math.abs(d1.getTime - d2.getTime) / 2)
		}
		def middle[T:Middle](x:T, y:T):T = {
			val mid = implicitly[Middle[T]]
			return mid.middle(x, y)
		}

		println(middle(1, 4))
		println(middle("abcd", "123456"))
		println(middle(new Date(0), new Date()))

		def _middle[T:Middle](x:T, y:T)(implicit mid:Middle[T]) = mid.middle(x, y)
		println(_middle(1, 4))
		println(_middle("abcd", "123456"))
		println(_middle(new Date(0), new Date()))
	}
}
