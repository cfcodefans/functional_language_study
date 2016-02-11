package cf.study.lang

import org.junit.{Assert, Test}

/**
 * Created by Administrator on 2015/7/8.
 */
class ControlStructureTests {

	@Test def testIf(): Unit = {
		{
			def foo(args: Array[String]): String = {
				var filename = "default.txt"
				if (!args.isEmpty)
					filename = args(0)
				return filename
			}

			Assert.assertEquals("default.txt", foo(Array()))
			Assert.assertEquals("something", foo(Array("something")))
		}

		{
			def foo(args: Array[String]): String = {
				val filename =
					if (!args.isEmpty) args(0)
					else "default.txt"
				return filename
			}
			Assert.assertEquals("default.txt", foo(Array()))
			Assert.assertEquals("something", foo(Array("something")))
		}

		{
			def foo(args: Array[String]): String =
					if (!args.isEmpty) args(0)
					else "default.txt"
			Assert.assertEquals("default.txt", foo(Array()))
			Assert.assertEquals("something", foo(Array("something")))
		}
	}

	@Test def testWhile(): Unit = {
		def gcdLoop(x: Long, y: Long): Long = {
			var a = x
			var b = y
			while (a != 0) {
				val temp = a
				a = b % a
				b = temp
			}
			b
		}

		Assert.assertEquals(gcdLoop(36, 32), 4)
		Assert.assertEquals(gcdLoop(36, 24), 12)
		Assert.assertEquals(gcdLoop(36, 18), 18)
	}

	@Test def testHighOrderFunc: Unit = {
		def call(op: (Int, Int) => Int)(a: Int, b: Int): Int = {
			println("before op")
			val result:Int = op(a, b)
			println("after op: " + result)
			return result
		}

		val add:(Int, Int) => Int = (a: Int, b: Int) =>{
			if (a > 5) {
				println("return a")
				return a
			}
			if (b < 5) {
				println("return b")
				return b
			}
			println("return a + b")
			return a + b
		}

		val callAdd: (Int, Int) => Int = call(add(_, _))
		println(callAdd)

		println("call(1, 2) = " + callAdd(1, 2))
		println("call(6, 2) = " + callAdd(6, 2))
	}
}
