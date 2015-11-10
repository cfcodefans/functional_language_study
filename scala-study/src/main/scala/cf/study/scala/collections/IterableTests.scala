package cf.study.scala.collections

import org.junit.Test

/**
 * Created by fan on 2015/9/17.
 */
class IterableTests {
	@Test def testLastAndHead: Unit = {
		val fruits: Iterable[String] = List("apples", "oranges", "pears", "bananas")
		println(fruits.last)
		println(fruits.head)
	}

	@Test def testFold() {
		val fruits: Iterable[String] = List("apples", "oranges", "pears", "bananas")
		println(fruits.fold("fruit: ")(_ + ", " + _))
	}

	@Test def testFoldLeft(): Unit = {
		val fruits: Iterable[String] = List("apples", "oranges", "pears", "bananas")
		println(fruits.foldLeft("fruit: ")(_ + ", " + _))
	}

	@Test def testFoldRight(): Unit = {
		val fruits: Iterable[String] = List("apples", "oranges", "pears", "bananas")
		println(fruits.foldRight("fruit: ")(_ + ", " + _))
	}
}
