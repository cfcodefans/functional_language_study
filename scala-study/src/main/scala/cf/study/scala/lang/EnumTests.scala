package cf.study.scala.lang

import org.junit.Test

/**
  * Created by fan on 2016/8/11.
  */

object WeekDayEnum extends Enumeration {
//	type WeekDay = Value

	def nextNameOrNull =
		if (nextName != null && nextName.hasNext) nextName.next() else null

//	class WeekDay(val fullname:String, id: Int = nextId, name: String = nextNameOrNull) extends Val(id, name) {
	class WeekDay(val fullname:String) extends Val {
		println(s"${id}:\t${fullname}")

		override def toString(): String = super.toString() + " => " + fullname
	}

	val Mon = new WeekDay("Monday")
	val Tue  = new WeekDay("Tuesday")
	val Wed  = new WeekDay("Wednesday")
	val Thu  = new WeekDay("Thursday")
	val Fri  = new WeekDay("Friday")
	val Sat  = new WeekDay("Saturday")
	val Sun  = new WeekDay("Sunday")

	def isWorkingDay(d: WeekDay) = !(d == Sat || d == Sun)
}

class EnumTests {
	import WeekDayEnum._

	@Test
	def testWeekDay = {
		println(WeekDayEnum.values.mkString("\n"))
	}
}
