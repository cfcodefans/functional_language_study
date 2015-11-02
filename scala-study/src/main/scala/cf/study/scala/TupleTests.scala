package cf.study.scala

import org.junit.Test

/**
 * Created by fan on 2015/9/14.
 */
class TupleTests {
	@Test def testTuple: Unit = {
		val t1:(Int, Char) = new Tuple2(1, 'a')
		println(t1)

		val t2:Tuple2[Int, Char] = t1
		println(t2)
	}
}
