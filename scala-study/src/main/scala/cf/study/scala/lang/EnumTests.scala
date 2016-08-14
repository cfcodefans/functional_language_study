package cf.study.scala.lang

import cf.study.scala.lang.EnumTests.WeekDay.WeekDay
import org.junit.Test

/**
  * Created by fan on 2016/8/11.
  */
object EnumTests {


	object WeekDay extends Enumeration {
		class _WeekDay(id: Int, name: String) extends Val(id, name) {
			val work:Boolean = id < 6
		}

		type WeekDay = _WeekDay
		val Mon, Tue, Wed, Thu, Fri, Sat, Sun = new _WeekDay(nextId, if (nextName != null && nextName.hasNext) nextName.next() else null)
	}

}

class EnumTests {

	import EnumTests._

	@Test def testWeekDay = {
		val wd: WeekDay = WeekDay.Mon
		println(wd)
		println(wd.getClass)
		println(wd.work)
	}
}
