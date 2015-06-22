package cf.study.lang

import java.math.BigInteger

import org.junit.Test
import org.junit.Assert

/**
 * Created by Administrator on 2015/6/21.
 */
class NextStepTests {
	@Test def parameterizeArrayWithTypes(): Unit = {
		val big = new BigInteger("12345")
		println(big)

		{
			val greetStrings = new Array[String](3)
			greetStrings(0) = "Hello"
			greetStrings(1) = ", "
			greetStrings(2) = "world!\n"

			for (i <-  0 to 2)
				print(greetStrings(i))

			println(greetStrings.getClass())
		}

		println()

		{
			val greetStrings: Array[String] = new Array[String](3)
			greetStrings(0) = "Hello"
			greetStrings(1) = ", "
			greetStrings(2) = "world!\n"

			for (i <-  (0).to(2))
				print(greetStrings(i))

			println(greetStrings.getClass())
		}

		{
			val greetStrings: Array[String] = new Array[String](3)
			greetStrings(0) = "Hello"
			greetStrings(1) = ", "
			greetStrings(2) = "world!\n"

			Assert.assertEquals(1 + 1, (1).+(1))
			Assert.assertEquals(greetStrings(1), greetStrings.apply(1))
			greetStrings(0) = "Holla"
			Assert.assertEquals("Holla", greetStrings(0))
			greetStrings.update(0, "Hello")
			Assert.assertEquals("Hello", greetStrings(0))
		}

		{
			val numNames = Array("zero", "one", "two")
			val numNames2 = Array.apply("zero", "one", "two")

			(0).to(numNames.length - 1).foreach(i => Assert.assertEquals(numNames(i), numNames2(i)))
		}

		{
			val letters = Array("a")
			letters.+:("b")
			println(letters.toList)
			println(("b" +: letters).toList)
			println((letters + "b"))
		}
	}

	@Test def useList(): Unit = {
		val oneTwoThree = List(1, 2, 3)
		println(oneTwoThree)

		val oneTwo = List(1, 2)
		val threeFour = List(3, 4)

		val oneTwoThreeFour = oneTwo ::: threeFour

		println(oneTwo + " and " + threeFour + " were not mutated.")
		println("Thus, " + oneTwoThreeFour + " is a new list.")

		val twoThree = List(2, 3)
		val _oneTwoThree = 1 :: twoThree
		println(_oneTwoThree)

		Assert.assertEquals(_oneTwoThree, twoThree.::(1))

		val __oneTwoThree = 1 :: 2 :: 3 :: Nil
		println(__oneTwoThree)
	}

	@Test def listMethods(): Unit = {
		{
			val emptyList = List()
			Assert.assertEquals(emptyList.size, 0) //def size: Int={...} doesn't take parameter, or parenthesis
			Assert.assertEquals(Nil.size, 0)
		}

		{
			val colorList = List("Cool", "tools", "rule")
			Assert.assertTrue(colorList.contains("Cool"))
			Assert.assertEquals(colorList(1), "tools")
			Assert.assertEquals(colorList.size, 3)
		}

		{
			val thrill = "Will" :: "fill" :: "until" :: Nil
			Assert.assertEquals(thrill(2), "until")

			Assert.assertEquals(thrill.count(s => s.length == 4), 2)
			Assert.assertFalse(thrill.drop(2).contains("fill"))
			Assert.assertTrue(thrill.contains("fill")) //drop returns new list
			Assert.assertFalse(thrill.dropRight(2).contains("until"))
			Assert.assertTrue(thrill.exists(s => s == "until"))
			Assert.assertEquals(thrill.filter(s => s.length() == 4), List("Will", "fill"))
			Assert.assertTrue(thrill.forall(s => s.endsWith("l")))
			Assert.assertEquals(thrill.head, "Will")
			Assert.assertEquals(thrill.init, List("Will", "fill"))
			Assert.assertFalse(thrill.isEmpty)
			Assert.assertEquals(thrill.last, "until")
			Assert.assertEquals(thrill.length, 3)
			Assert.assertEquals(thrill.map(s => s + "y"), List("Willy", "filly", "untily"))
			Assert.assertEquals(thrill.mkString(", "), "Will, fill, until")
			Assert.assertEquals(thrill.dropWhile(s => s.length == 4), List("until"))
			Assert.assertEquals(thrill.reverse, List("until", "fill", "Will"))
			Assert.assertEquals(thrill.sortWith((s, t) => s.charAt(0).toLower < t.charAt(0).toLower), List("fill", "until", "Will"))
		}

		{
			val abcd = List("a", "b") ::: List("c", "d")
			Assert.assertEquals(abcd(0), "a")
			Assert.assertEquals(abcd(1), "b")
			Assert.assertEquals(abcd(2), "c")
			Assert.assertEquals(abcd(3), "d")

			abcd.updated(2, "C")
			Assert.assertNotSame(abcd(2), "C")
			Assert.assertEquals(abcd.updated(2, "C").apply(2), "C")
		}
		{
			val mixList = List(1, "2", BigInteger.TEN)
			println(mixList.getClass)
		}
	}

	@Test def tuple(): Unit = {

	}
}



