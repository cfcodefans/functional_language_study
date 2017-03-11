package cf.study.scala.lang

import java.util
import java.util.Map
import java.util.HashMap


import org.junit.Test

/**
 * Created by fan on 2015/6/19.
 */
class SyntaxTests {

	@Test def semicolons(): Unit = {
		//Trailing equals sign indicates more code on next line
		def equalsign() = {
			val reallySuperLongValueNameThatGoesOnForeverSoYouNeedANewLone =
			"Wow that was a long value name"

			println(reallySuperLongValueNameThatGoesOnForeverSoYouNeedANewLone)
		}

		//Trailing opening curly brace indicates more code on next line
		def equalsign2(s: String) = {
			println("equalsign2: " + s)
		}

		//Trailing comma, operator, etc. indicates more code on next line
		def commas(s1: String,
		           s2: String) = {
			println("comma: " + s1 +
					"," + s2)
		}

		equalsign()
		equalsign2("hello")
		commas("yes", "no?")
	}

	@Test def varDeclarations(): Unit = {
		//immutable
		val array:Array[String] = new Array(5)

//  can't assign a val to a new reference
//		array = new Array(2)

		var stockPrice: Double = 100
		println(stockPrice)
		stockPrice = 10
		println(stockPrice)
	}

	@Test def methodDefaultAndNamedArguments(): Unit = {
		{
			object StringUtil {
				def joiner(strings: List[String], separator: String): String = strings.mkString(separator)
				def joiner(strings: List[String]): String = joiner(strings, " ")
			}

			import StringUtil._ //Import the joiner methods
			println(joiner(List("Programming", "Scala")))
		}

		{
			object StringUtil {
				def joiner(strings: List[String], separator: String = " "): String = strings.mkString(separator)
			}

			import StringUtil._ //Import the joiner methods
			println(joiner(List("Programming", "Scala")))

			println(joiner(strings = List("Programming", "Scala")))
			println(joiner(List("Programming", "Scala"), " "))
			println(joiner(List("Programming", "Scala"), separator = " "))
			println(joiner(strings = List("Programming", "Scala"), separator = " "))

			println(joiner(separator = " ", strings = List("Programming", "Scala")))
		}
	}

	@Test def contructorNamedArguments(): Unit = {
		{
			object OptionalUserProfileInfo {
				val UnknownLocation = ""
				val UnKnownAge = -1
				val UnknownSite = ""
			}

			class OptionalUserProfileInfo(
			                             location: String = OptionalUserProfileInfo.UnknownLocation,
			                             age: Int = OptionalUserProfileInfo.UnKnownAge,
			                             webSite: String = OptionalUserProfileInfo.UnknownSite
				                             ) {
//				@Override def toString(): String= {
//					this.toString() + "\tlocation: " + location + "\tage: " + age + "\twebSite: " + webSite
//				}
			}
			println(new OptionalUserProfileInfo())
			println(new OptionalUserProfileInfo(age = 29))
			println(new OptionalUserProfileInfo(age = 29, location = "Earth"))
		}
	}

	@Test def nestedMethods(): Unit = {
		def factorial(i: Int): Int = {
			def fact(i: Int, accumulator: Int): Int = {
				if (i <= 1)
					accumulator
				else
					fact(i - 1, i * accumulator)
			}

			fact(i, 1)
		}

		println(factorial(0))
		println(factorial(1))
		println(factorial(2))
		println(factorial(3))
		println(factorial(4))
		println(factorial(5))

		def countTo(n: Int): Unit = {
			def count(i: Int): Unit = {
				if (i <= n) {
					println(i)
					count(i + 1)
				}
			}
			count(1)
		}

		countTo(5)
	}

	@Test def inferringTypeInfo(): Unit = {
		val intToStringMap: Map[Integer, String] = new HashMap
		val intToStringMap2 = new HashMap[Integer, String]
	}

	@Test def explicitReturnType(): Unit = {
		def upCase(s: String): String = {
			if (s.length == 0)
				//Method has return statement; needs result type
				return s
			else
				s.toUpperCase()
		}
		println(upCase(""))
		println(upCase("hELLO"))

		def fibonacci(n: Int): Int = {
			if (n <= 1)
				return n
			return fibonacci(n - 2) + fibonacci(n - 1)
		}

		println(fibonacci(0))
		println(fibonacci(1))
		println(fibonacci(2))
		println(fibonacci(3))
		println(fibonacci(4))
		println(fibonacci(5))
		println(fibonacci(6))
		println(fibonacci(7))
	}

	@Test def testThrow(): Unit = {
	}
}
