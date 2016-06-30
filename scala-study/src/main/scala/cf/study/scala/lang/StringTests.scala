package cf.study.scala.lang

import java.util._

import org.junit.Test

/**
 * Created by fan on 2016/2/8.
 */
class StringTests {
	@Test def testSplit: Unit = {
		println("abc".split(',').toList)
		println("/data".split(',').toList)
		println("\u000A")
	}
}
