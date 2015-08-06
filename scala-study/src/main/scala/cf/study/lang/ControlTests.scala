package cf.study.lang

import org.junit.{Assert, Test}

/**
 * Created by fan on 2015/7/6.
 */
class ControlTests {
	@Test
	def testIf(): Unit = {
		{
			val filename = "default.txt"
			if (filename != null)
				Assert.fail()
		}

		{
			var filename: String = null
			filename = if (filename == null) "default.txt" else filename
			Assert.assertEquals(filename, "default.txt")
		}
	}

	@Test
	def testWhile(): Unit = {
		def gcdLoop(x: Long, y: Long): Long = {
			var a = x
			var b = y
			while (a != 0) {
				val tmp = a
				a = b % a
				b = tmp
			}
			b
		}

		Assert.assertEquals(gcdLoop( 24, 16), 4)

		{
			var i:Int = 0
			do {
				print(i)
				i += 1
			} while (10 > i)
		}
	}

	@Test
	def testFor(): Unit = {
		
	}
}
