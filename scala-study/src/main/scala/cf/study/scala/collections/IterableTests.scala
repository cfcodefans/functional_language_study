package cf.study.scala.collections

import org.junit.Test

/**
 * Created by fan on 2015/9/17.
 */
class IterableTests {
	@Test def test: Unit = {
		val fruits: Iterable[String] = List("apples", "oranges", "pears", "bananas")
		println(fruits.last)
		println(fruits.head)
	}
}
