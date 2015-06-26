package cf.study.lang

import org.junit.{Assert, Test}

/**
 * Created by fan on 2015/6/25.
 */
class BasicTypeAndOperTests {
	@Test def literals(): Unit = {
		val hex = 0x5
		Assert.assertEquals(hex, 5)
		val hex2 = 0x00FF
		Assert.assertEquals(hex2, 255)
		val magic = 0xcafebabe
		Assert.assertEquals(magic, -889275714)

//		val oct = 035
//		Assert.assertEquals(oct, 3 * 8 + 5)
//		val nov = 0777
//		Assert.assertEquals(nov, 511)

		val a = 'A'
		val c = '\101'
		Assert.assertEquals(a, c)
		val d = '\u0041'
		Assert.assertEquals(a, d)

		println("""Welcome to Ultamix 3000.
			  Type "Help" for help.""")
		println("""|Welcome to Ultamix 3000.
			  |Type "Help" for help.""".stripMargin)

		val hello = "hello"
		println(hello)

		val escape = "\\\"\'"
		println(escape)

//		val quote = '""'
		val quote = "''"
		println(quote)
	}

	@Test def testSymbol(): Unit = {
		val s = 'aSymbol
		println(s, s.name)

		def takeSymbol(s: Symbol, value: Any): Unit = {
			println(s)
		}

//		takeSymbol("what", null) //has to be symbol
		takeSymbol('what, null)
		takeSymbol(null, null)
	}

	@Test def operatorsAndMethods(): Unit = {
		println(1 + 2) //Scala invokes (1).+(2)
		println((1).+(2))

		println(1 + 2L) //Scala invokes (1).+(2)
		println((1).+(2L))

		println(1L + 2) //Scala invokes (1).+(2)
		println((1L).+(2))

		def pi=3.14
		println(pi)

		val s = "Hello, world!"
		println(s, "s indexOf 'o'", s indexOf 'o')
		println(s, "s indexOf('o')", s indexOf('o'))
		println(s, "s.indexOf('o')", s.indexOf('o'))

		println(-2.0, "(2.0).unary_- ", (2.0).unary_-)
	}

	@Test def arithmeticOperations(): Unit = {
		println("1.2 + 2.3", 1.2 + 2.3)
		println("3 - 1", 3 - 1)
		println("'b'-'a'", 'b' - 'a')
		println("2L * 3L", 2L * 3L)
		println("11 / 4", 11 / 4)
		println("11 % 4", 11 % 4)
		println("11 % -4", 11 % -4)
		println("-11 % 4", -11 % 4)
		println("11.0f / 4.0f", 11.0f / 4.0f)
		println("11.0f % 4.0f", 11.0f % 4.0f)

		println("0.5f % 0.3f", 0.5f % 0.3f)

		val neg = 1 + -3
		println("neg =", neg, "\t-neg=", -neg)
		val y = +3
		println("y =", y)
	}

	@Test def relationalAndLogicalOperations(): Unit = {
		println("1 > 2", 1 > 2)
		println("1 < 2", 1 < 2)
		println("1.0 <= 1.0", 1.0 <= 1.0)
		println("1.0 >= 1.0", 1.0 >= 1.0)
		println("3.5f >= 3.6f", 3.5f >= 3.6f)
		println("'a' >= 'A'", 'a' >= 'A')
		println("!true", !true)
//		println("!true", !!true) //can't compile

		val toBe = true
		println("toBe", toBe)
		val question = toBe || !toBe
		println("toBe || !toBe", question)
		val paradox = toBe && !toBe
		println("toBe && !toBe", paradox)

	}


}
