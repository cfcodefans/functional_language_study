package cf.study.scala.collections

import org.junit.{Assert, Test}

/**
 * Created by fan on 2015/9/14.
 */
class ListTests {
	@Test def testList: Unit = {
		//List of Strings
		val fruits: List[String] = List("apples", "oranges", "pears", "bananas")
		println(fruits)
		Assert.assertEquals(fruits.length, 4)

		println("fruits(1)", fruits(1))
		//		println("fruits(1, 3)", fruits(1, 3))

		Assert.assertEquals(fruits(1), "oranges")
		Assert.assertEquals(fruits.take(1), List("apples"))
		Assert.assertEquals(fruits.take(2), List("apples", "oranges"))
		Assert.assertEquals(fruits.length, 4)
	}

	@Test def testNestedList: Unit = {
		val plant: List[List[String]] = List(List("apple", "pear"), List("grass", "weed"), List("palm", "seaweed"))
		plant.foreach(_list => _list.foreach(println))
	}

	@Test def testHead: Unit = {
		val fruits: List[String] = List("apples", "oranges", "pears", "bananas")
		println(fruits.head)
	}

	@Test def testTail: Unit = {
		val fruits: List[String] = List("apples", "oranges", "pears", "bananas")
		println(fruits.tail)
	}

	@Test def testIsEmpty: Unit = {

	}
}
