package cf.study.scala.collections

import java.util.Date

import org.apache.commons.lang3.builder.ToStringBuilder
import org.junit.{Assert, Test}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

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

		println(fruits + "what")
		println(fruits :: List[String]("what"))
		println(fruits ::: List[String]("what"))
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

	@Test def testLast: Unit = {
		val fruits: List[String] = List("apples", "oranges", "pears", "bananas")
		println(fruits.last)
	}

	@Test def testInit: Unit = {
		val fruits: List[String] = List("apples", "oranges", "pears", "bananas")
		println(fruits.init)
	}

	@Test def testIsEmpty: Unit = {
		val list = List()
		Assert.assertTrue(list.isEmpty)
		Assert.assertFalse(list + "" isEmpty)
	}

	@Test def testReverse(): Unit = {
		println(List(1, 2, 3, 4, 5).reverse)
	}

	@Test def contructLists(): Unit = {
		val fruits = "apples" :: ("oranges" :: ("pears" :: Nil))
		println(fruits.size)
		println(ToStringBuilder.reflectionToString(fruits))

		val nums = 1 :: (2 :: (3 :: (4 :: Nil)))
		Assert.assertEquals(nums.head, 1)
		Assert.assertEquals(nums.last, 4)
	}

	@Test def testInsert(): Unit = {
		val nums: ListBuffer[Int] = ListBuffer(1, 2, 3, 4)
		Assert.assertEquals(2, nums(1))
		nums.insert(1, 1)
		Assert.assertEquals(1, nums(1))
		Assert.assertEquals(2, nums(2))
	}

	@Test def testInsertSort(): Unit = {
		def insert(x: Int, xs: List[Int]): List[Int] =
			if (xs.isEmpty || x <= xs.head) x :: xs
			else xs.head :: insert(x, xs.tail)

		def isort(xs: List[Int]): List[Int] =
			if (xs.isEmpty) xs
			else insert(xs.head, isort(xs.tail))

		val nums: List[Int] = List(3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5, 8, 9, 7)
		println(isort(nums))
	}

	@Test def testListPatterns(): Unit = {
		val fruits: List[String] = List("Apple", "Banana", "Cherry")
		val List(a, b, c) = fruits
		Assert.assertEquals("Apple", a)
		Assert.assertEquals("Banana", b)
		Assert.assertEquals("Cherry", c)

		//		val List(head, rest) = fruits //match error
		val head :: rest = fruits
		Assert.assertEquals("Apple", head)
		Assert.assertEquals(fruits.tail, rest)
	}

	@Test def testFirstOrderListMethods(): Unit = {
		Assert.assertEquals(List(1, 2) ::: List(3, 4, 5), List(1, 2, 3, 4, 5))
		Assert.assertEquals(List() ::: List(1, 2, 3), List(1, 2, 3))
		Assert.assertEquals(List(1, 2, 3) ::: List(4), List(1, 2, 3, 4))
	}

	@Test def testPrefixAndSuffix(): Unit = {
		val abcde = List('a', 'b', 'c', 'd', 'e')
		val List(a, b, c, d, e) = abcde
		Assert.assertEquals(abcde.take(2), List(a, b))
		Assert.assertEquals(abcde.takeRight(2), List(d, e))
		Assert.assertEquals(abcde.drop(2), List(c, d, e))
		Assert.assertEquals(abcde.dropRight(2), List(a, b, c))
		Assert.assertEquals(abcde.splitAt(2), (List(a, b), List(c, d, e)))
		Assert.assertEquals(abcde.apply(2), c)
	}

	@Test def testFlatten(): Unit = {
		println(List(List(1, 2), List('a'), List(), List(new Date())).flatten)
	}

	@Test def testIndices(): Unit = {
		val abcde = List('a', 'b', 'c', 'd', 'e')
		println(abcde.indices)
	}

	@Test def testZip(): Unit = {
		val abcde = List('a', 'b', 'c', 'd', 'e')
		println(abcde.indices.toList.zip(abcde))

		println(abcde.zip(List(1, 2, 3)))

		println(abcde.zipWithIndex)

		println(abcde.zipWithIndex.unzip)
	}

	@Test def testWithString(): Unit = {
		val abcde = List('a', 'b', 'c', 'd', 'e')
		println(abcde.mkString("\t"))
		println(abcde.mkString("[", ", ", "]"))
	}

	@Test def testConvertingList(): Unit = {
		val abcde = List('a', 'b', 'c', 'd', 'e')
		println(abcde)
		val array = abcde.toArray
		println(array.mkString(", "))
		array(1) = 'p'
		println(array.mkString(", "))
		println(abcde)

		val it = abcde.iterator
		println(it.next)
		println(it.next)
	}

	@Test def testMergeSort() {
		def msort[T](less: (T, T) => Boolean)(xs: List[T]): List[T] = {
			def merge(xs: List[T], ys: List[T]): List[T] =
				(xs, ys) match {
					case (Nil, _) => ys
					case (_, Nil) => xs
					case (x :: xs1, y :: ys1) =>
						if (less(x, y)) x :: merge(xs1, ys)
						else y :: merge(xs, ys1)
				}

			val n = xs.length / 2
			if (n == 0) xs
			else {
				val (ys, zs) = xs.splitAt(n)
				merge(msort(less)(ys), msort(less)(zs))
			}
		}

		val nums: List[Int] = List(3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5, 8, 9, 7)
		println(msort((_1: Int, _2: Int) => _1 < _2)(nums))
		println(msort((_1: Int, _2: Int) => _1 > _2)(nums))
	}

	@Test def testCollect(): Unit = {
		val samples: List[Int] = 1.to(10).toList
		val isEven: PartialFunction[Int, String] = {
			case x if x % 2 == 0 => x + " is even"
		}

		val evenNums = samples.collect(isEven)
		println(evenNums)

		val isOdd: PartialFunction[Int, String] = {
			case x if x % 2 != 0 => x + " is odd"
		}

		val nums = samples.map(isEven.orElse(isOdd))
		println(nums)
	}

	@Test def testFoldLeft(): Unit = {
		val samples: List[Int] = 1.to(10).toList
		val sum: Int = samples.sum
		println(sum)

		println(samples.foldLeft(0)((a, b) => a + b))
		println(samples.foldLeft("join: ")((s, i) => s + i + ", "))

		println(1.to(3).foldLeft(1)((_n, _e) => _n * _e))
	}

	def factorial(n:Int):Double = n.toLong.to(1).foldLeft(n.toLong)((_n, _e) => _n * _e).toDouble

	@Test def testCombination(): Unit = {
//		import scala.collection.mutable._

		val result:ListBuffer[Set[Int]] = ListBuffer.empty

		def cmbNumber(n:Int, m:Int):Int = (factorial(n) / (factorial(m) * factorial(n - m))).toInt

		def pick(src:Set[Int], n:Int, cmb:Set[Int], _re:ListBuffer[Set[Int]] = ListBuffer.empty): Unit = {
//			if (n >= src.size && n > 0) {
//				_re += src
//				return
//			}

			if (n == 0) {
				_re += cmb
				return
			}

			var picked:Set[Int] = Set.empty
			src.map((i:Int) => {
				picked += i
				pick(src -- picked, n - 1, cmb + i, _re)
			})
		}

		pick(1.to(6).toSet, 3, Set.empty[Int], result)
		println(cmbNumber(6, 3), result.size)
		result.foreach(cmb => println(cmb.mkString(" ")))
	}
}
