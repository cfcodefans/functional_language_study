package cf.study.scala.collections

import org.junit.Test

/**
 * Created by fan on 2016/1/25.
 */
class MapTests {

	@Test def testMap: Unit = {
		val m = Map((1, -1))
		println(m)
		println(m(1))
		println(m(0))
	}
}
