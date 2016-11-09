package cf.study.scala.lang

import cf.study.scala.lang.ExtractorTests.TestNum
import org.junit.Test

/**
  * Created by fan on 2016/9/14.
  */
object ExtractorTests {

	object TestNum {
	}

	def unapply(arg: TestNum): Option[Double] = {
		println(s"TestNum.unapply(${arg.num})")
		None
	}
	case class TestNum(num: Double)
}

class ExtractorTests {
	@Test def testUnapply: Unit = {
		val tn: TestNum = TestNum(4)
		tn match {
			case TestNum(4) => println("matched")
			case _ => println("unmatched")
		}
	}
}
