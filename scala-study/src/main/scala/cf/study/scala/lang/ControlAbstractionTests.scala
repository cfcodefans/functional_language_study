package cf.study.scala.lang

import java.io.File
import java.util.{Calendar, TimeZone}

import org.junit.{Assert, Test}

import scala.util.Random

/**
 * Created by fan on 2015/11/3.
 */
class ControlAbstractionTests {

	object FileMatcher {
		private def filesHere = (new File(".")).listFiles

		def filesEnding(query:String) =
			for (file <- filesHere; if file.getName.endsWith(query))
				yield file

		def filesContaining(query: String) =
			for (file <- filesHere; if file.getName.contains(query))
				yield file

		def filesRegex(query: String) =
			for (file <- filesHere; if file.getName.matches(query))
				yield file
	}

	object AbstractFileMatcher {
		private def filesHere = (new File(".")).listFiles
		private def filesMatching(matcher: String => Boolean) =
			for (file <- filesHere; if matcher(file.getName))
				yield file

		def filesEnding(query: String) =
			filesMatching(_.endsWith(query))
		def filesContaining(query: String) =
			filesMatching(_.contains(query))
		def filesRegex(query: String) =
			filesMatching(_.matches(query))
	}

	@Test def codeSimplification(): Unit = {
		def containsNeg(nums: List[Int]): Boolean = {
			for (num <- nums)
				if (num < 0)
					return true
			return false;
		}
		Assert.assertTrue(containsNeg(List(1, 2, -3, 4)))
		Assert.assertFalse(containsNeg(List(1, 2, 3, 4)))

		def _containsNeg(nums: List[Int]) = nums.exists(_ < 0)
		Assert.assertTrue(_containsNeg(List(1, 2, -3, 4)))
		Assert.assertFalse(_containsNeg(List(1, 2, 3, 4)))

		def containsOdd(nums: List[Int]): Boolean = {
			for (num <- nums)
				if (num % 2 == 1)
					true
			false
		}

		def _containsOdd(nums: List[Int]) = nums.exists(_ % 2 == 1)
	}

	@Test def currying(): Unit = {
		def plainOldSum(x: Int, y: Int) = x + y
		println(plainOldSum(1, 2))

		def curriedSum(x: Int)(y: Int) = x + y
		val f = curriedSum(1)(_: Int)
		println(f)
		Assert.assertEquals(f(2), 3)
		Assert.assertEquals(curriedSum(1)(2), 3)
		val f_ = curriedSum(_: Int)(2)
		println(f_)
		Assert.assertEquals(f_(2), 4)

		val plusOne = curriedSum(1)_
		Assert.assertEquals(plusOne(0), 1)

		val plusTwo = curriedSum(2)_
		Assert.assertEquals(plusTwo(5), 7)
	}

	@Test def newControlStructure(): Unit = {
		def twice(op: Int => Int, x: Int) = op(op(x))
		Assert.assertEquals(twice(_ + 1, 3), 5)
		Assert.assertEquals(twice(d => d + 1, 3), 5)
		Assert.assertEquals(twice(d => d * d, 3), 81)
		// can't compile
//		Assert.assertEquals(twice(_ * _, 3), 81)

		println("Hello, world!")
		println{"Hello, world!"}
		val hwf: String = { "Hello, world!"	}
		println(hwf)
		println(hwf.getClass)

		println({ "Hello, world!"	})
		println({ "Hello, world!"	}.getClass)

		val str: String = {
			val a = Random.nextInt
			if (a % 2 == 1) "odd" else "even"
		}

		println(str)
		println({str})
		println({{str}})

//		println({0, 1, 2}) //can't work

		def now(tz: TimeZone)(op: Calendar => Int): Int = {
			op(Calendar.getInstance(tz))
		}

		val month = now(_: TimeZone)(_.get(Calendar.MONTH))
		val hour = now(_: TimeZone)(_.get(Calendar.HOUR))

		print(hour(TimeZone.getDefault))
	}

	@Test def byNameParam(): Unit = {
		def myAssert(predicate: () => Boolean) =
			if (!predicate()) throw new AssertionError

		myAssert(() => 5 > 3)
		//myAssert(5 > 3) //doesn't work, mismatch type between boolean and function

		def byNameAssert(predicate: => Boolean) =
			if (!predicate) throw new AssertionError

		byNameAssert(5 > 3)
	}
}
