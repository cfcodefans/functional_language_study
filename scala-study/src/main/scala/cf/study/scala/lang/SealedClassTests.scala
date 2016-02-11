package cf.study.scala.lang

import org.junit.Test

/**
 * Created by fan on 2015/12/29.
 */
class SealedClassTests {
	sealed class BinOpers {
		def and(a: Boolean, b: Boolean): Boolean = a && b
	}

	@Test def testAnd(): Unit = {
//		println(true and false)
	}
}
