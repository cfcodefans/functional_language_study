package cf.study.scala.lang

import java.io.{FileNotFoundException, File}
import java.nio.file.{Paths, Path}
import java.time.Month

import org.junit.{Assert, Test}

import scala.util.Random

import scala.util.control.Breaks._

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

		Assert.assertEquals(gcdLoop(24, 16), 4)

		{

		}

		{
			var i: Int = 0
			do {
				print(i)
				i += 1
			} while (10 > i)
		}
	}

	@Test
	def testFor(): Unit = {
		val filesHere = (new File(".")).listFiles()

		{
			for (file <- filesHere)
				println(file)
		}
		println()

		{
			for (i <- 0 to filesHere.length - 1)
				println(filesHere(i))
		}
		println()

		{
			for (file <- filesHere if file.isFile)
				println(file)
		}
		println()

		{
			for (file <- filesHere
			     if file.isFile
			     if file.isHidden)
				println(file)
		}
		println()

		{
			for (i <- 1 to 4)
				println("Iteration: " + i)
		}
		println()
	}

	@Test
	def testNestedIteration() {
		{
			for (i <- 1 to 4; j <- Array(2 * i))
				println(i, j)
		}

		{
			for (i <- 1 to 4; j <- Array(2 * i); k <- Array(j * 2))
				println(i, j, k)
		}
	}

	@Test def testYeild(): Unit = {
		{
			val evens = for (i <- 0 to 9 if i % 2 == 0) yield i
			println(evens)
		}

		{
			val evens = for (i <- 0 to 9 if i % 2 == 0) yield {
				"even: " + i
			}
			println(evens)
		}
	}

	@Test def testCatchClause() {
		def foo(filename: String): File = {
			if (filename isEmpty)
				throw new IllegalArgumentException("filename is empty")

			val file: File = Paths.get(filename).toFile
			if (!file.exists())
				throw new FileNotFoundException("file:%s is not found".format(filename))

			file
		}

		try {
			foo("")
		} catch {
			case e: Exception => {
				e.printStackTrace()
			}
		}

		try {
			foo("what")
		} catch {
			case e: Exception => {
				e.printStackTrace()
			}
		}

		try {
			foo("what")
		} catch {
			case e: IllegalArgumentException => {
				e.printStackTrace()
			}
			case e: FileNotFoundException => {
				println(e)
			}
		}
	}

	@Test def testIterationAndException: Unit = {
		val array:Array[Int] = (1 to 10).toArray

		try {
			array.foreach(i => if (i == 5) throw new Exception("5!"))
			println("not 5")
		} catch {
			case e: Exception => println(e)
		}


		try {
			for (i <- array) {
				if (i == 5) throw new Exception("5!")
			}
			println("not 5")
		} catch {
			case e: Exception => println(e)
		}
	}

	@Test def testFinally: Unit = {
		def foo(): Int = {
			try {
				return 1
			} finally {
				return 2
			}
			return 3
		}

		Assert.assertEquals(foo(), 2)
	}

	@Test def testMatch(): Unit = {
		def _match(month: Month): Unit = {
			month match {
				case Month.JANUARY => println("1st")
				case _ => println(month)
				case Month.FEBRUARY => println("2nd")
			}
		}

		_match(null)
		_match(Month.FEBRUARY)

		val month = Month.values()(Random.nextInt() % 12)
		val temperature = month match {
			case Month.JANUARY => "cold"
			case Month.FEBRUARY => "frezzing"
			case Month.MARCH => "chill"
			case Month.APRIL => "mild"
			case Month.MAY => "moderate"
			case Month.JUNE => "warm"
			case Month.JULY => "hot"
			case Month.AUGUST => "canicule"
			case Month.SEPTEMBER => "warm"
			case Month.OCTOBER => "moderate"
			case Month.NOVEMBER => "cool"
			case Month.DECEMBER => "cold"
		}
		println(month, temperature)
	}

	@Test
	def testBreakable(): Unit = {
		{/*
		 Class Breaks in package scala.util.control
offers a break method, which can be used to exit the an enclosing block
that's marked with breakable.
*/
			var v: Int = 0
			breakable {
				for (i <- 1 to 10) {
					v = i
					if (i > 5)
						break
				}
			}
			Assert.assertEquals(v, 6)
		}

		{
			var i:Int = 0
			breakable({ //breakable is a method!
				while (true) {
					i = i + 1
					if (i > 5)
						break
				}
			})
			Assert.assertEquals(i, 6)
		}

		{
			breakable({
				1.to(10).foreach(i => {
					println(i)
					if (i > 5) break
				})
			})
		}
	}

	@Test
	def testScope(): Unit = {
		def printMultiTable(): Unit = {
			var i = 1
			// only i in scope here
			while (i <= 10) {
				var j = 1
				// both i and j in scope here
				while (j <= 10) {
					val prod = (i * j).toString
					// i, j and prod in scope here
					var k = prod.length
					// i, j, prod and k in scope here
					while (k < 4) {
						print(" ")
						k += 1
					}
					print(prod)
					j += 1
				}
				// i and j still in scope; prod and k out of scope
				println()
				i += 1
			}
			// i still in scope; j, prod, and k out of scope
		}

		printMultiTable()

		{
			val a = 1
			//val a = 1 // Doesn't compile
			println(a);
			{
				val a = 2
				println(a)
			}
		}

		{
			// returns a row as a sequence
			def makeRowSeq(row: Int) =
				for (col <- 1 to 10) yield {
					val prod = (row * col).toString
					val padding = " " * (4 - prod.length)
					padding + prod
				}
			// returns a row as a string
			def makeRow(row: Int) = makeRowSeq(row).mkString
			// returns tables as a string with one row per line
			def multiTable() = {
				val tableSeq = //a sequence of row strings
					for (row <- 1 to 10) yield makeRow(row)
				tableSeq.mkString("\n")
			}

			println(multiTable())
		}
	}
}
