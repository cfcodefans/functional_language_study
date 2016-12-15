package cf.study.scala.collections

import cf.study.TestUtils
import org.junit.Test

/**
  * Created by fan on 2016/11/14.
  */
class TraversableTests {

	@Test def testGroupBy(): Unit = {
		val longs: List[Long] = TestUtils.pi2Longs(200)
		println(longs.groupBy(_l => _l).mkString("\n"))
		println(longs.groupBy(_l => _l % 2 == 1).mkString("\n"))
	}

	@Test def testGrouped(): Unit = {
		val longs: List[Long] = TestUtils.pi2Longs(200)
		println(longs.grouped(25).mkString("\n"))
	}
}
