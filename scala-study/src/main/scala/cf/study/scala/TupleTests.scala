package cf.study.scala

import org.apache.commons.lang3.builder.ToStringBuilder
import org.junit.{Assert, Test}

/**
 * Created by fan on 2015/9/14.
 */
class TupleTests {
	@Test def testTuple: Unit = {
		val t1:(Int, Char) = new Tuple2(1, 'a')
		println(t1)

		val t2:Tuple2[Int, Char] = t1
		println(t2)

		println(ToStringBuilder.reflectionToString((1, 'a')))

		val (_1, _a) = (1, 'a')
		Assert.assertEquals(_1, 1)
		Assert.assertEquals(_a, 'a')
	}
}
