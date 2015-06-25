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
	}
}
