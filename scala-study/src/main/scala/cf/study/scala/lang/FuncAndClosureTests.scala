package cf.study.scala.lang


import org.junit.Test

import scala.io.Source


/**
  * Created by fan on 2015/8/10.
  */

object LongLines {
	def processFile(filename: String, width: Int): Unit = {
		val source = Source.fromFile(filename)
		for (line <- source.getLines())
			processLine(filename, width, line)
	}

	private def processLine(filename: String, width: Int, line: String): Unit = {
		if (line.length > width)
			println("%s: %s".format(filename, line.trim))
	}

	def _processFile(filename: String, width: Int): Unit = {
		def processLine(filename: String, width: Int, line: String): Unit = {
			if (line.length > width)
				println("%s: %s".format(filename, line.trim))
		}

		val source = Source.fromFile(filename)
		for (line <- source.getLines())
			processLine(filename, width, line)
	}
}

//def func() {
//	println(func)
//}

class FuncAndClosureTests {
	@Test def testFuncLiteral: Unit = {
		val fruits: Iterable[String] = List("apples", "oranges", "pears", "bananas")
		val toLen: (String) => Int = (f: String) => f.length
		println(toLen)
		fruits.map(toLen).foreach(println)

		println
		fruits.map((f: String) => f.length).foreach(println)

		println
		//Formally speaking return is defined as always returning from the nearest enclosing named method
		fruits.map((f: String) => {
			if (f.startsWith("b"))
				f.charAt(0).toInt
			f.length
		}).foreach(println)
	}

	@Test def testFirstClassFunc(): Unit = {
		var increase = (x: Int) => x + 1
		println(increase)
		println(increase(10))
		println(increase.apply(10))

		increase = (x: Int) => {
			println("we")
			println("are")
			println("here")
			x + 1
		}

		println(increase)
		println(increase(10))
		println(increase.apply(10))
	}

	@Test def testLambda(): Unit = {
		val list: List[Long] = MathTests.pi2Longs(10)
		val gt5 = (x: Long) => x > 5

		println(list.filter(gt5).toList)
		println(list.filter((x: Long) => x > 5).toList)
		println(list.filter(_ > 5).toList)


		val oneToTen: List[Int] = (0 to 10).toList
		println((0 /: oneToTen) (_ + _))
	}

	@Test def testPartiallyAppliedFunc(): Unit = {
		def sum(a: Int, b: Int, c: Int) = a + b + c
		println(sum(_: Int, _: Int, _: Int))
		val a = sum _
		println(a)
		println(a(1, 2, 3))
		println(a.apply(1, 2, 3))
		println(a.curried.apply(1).apply(2).apply(3))

		val b = sum(1, _: Int, 3)
		println(b(2))
		println(b(5))

		def printFunc(f: (Int, Int, Int) => Int) = println(f)
		printFunc(sum)
	}

	@Test def testClosure(): Unit = {
		var more = 1
		val addMore = (x: Int) => x + more
		println((addMore(10)))
		more = 2
		println((addMore(10)))

		def makeIncreaser(more: Int) = (x: Int) => x + more
		val inc1 = makeIncreaser(1)
		println(inc1(5))
	}

	@Test def specialFuncCalls(): Unit = {
		def echo(args: String*) =
			for (arg <- args) println(arg)

		echo()
		echo("one")
		echo("hello", "world!")

		val arr = Array("What's", "up", "doc?")
		//		error: type mismatch; found : Array[java.lang.String]	required: String
		//		echo(arr)
		echo(arr: _*)
	}

	@Test def tailRecursion(): Unit = {
		val answer: Double = 0.0
		def isGoodEnough(guess: Double): Boolean = Math.abs(answer - guess) < 0.001

		def improve(guess: Double): Double =
			(answer + guess) / 2

		def approximate(guess: Double): Double =
			if (isGoodEnough(guess)) guess
			else approximate(improve(guess))

		def approximateLoop(initialGuess: Double): Double = {
			var guess = initialGuess
			while (!isGoodEnough(guess))
				guess = improve(guess)
			guess
		}

		println(approximate(10))
		println(approximateLoop(10))

		def boom(x: Int): Int =
			if (x == 0) throw new Exception("Boom!")
			else boom(x - 1) + 1 //This is not tail recursive, because it performs an increment operation after the recursive call

		def _boom(x: Int): Int =
			if (x == 0) throw new Exception("Boom!")
			else 1 + _boom(x - 1) //This is not tail recursive, because it performs an increment operation after the recursive call

		try {
			boom(3)
		} catch {
			case e: Exception => e.printStackTrace()
		}
		println()
		try {
			_boom(3)
		} catch {
			case e: Exception => e.printStackTrace()
		}

		def bang(x: Int): Int =
			if (x == 0) throw new Exception("Bang!")
			else bang(x - 1)

		try {
			bang(3)
		} catch {
			case e: Exception => e.printStackTrace()
		}

		//indirect recursion can't be turned into tail recursion
		def isEven(x: Int): Boolean =
		if (x == 0) true else isOdd(x - 1)
		def isOdd(x: Int): Boolean =
			if (x == 0) false else isEven(x - 1)
	}

	@Test def functionLiteral(): Unit = {
		def addOne(v: Int = 0): Int = v + 1
		println(addOne())
		println(addOne(1))
		def printlnFunc(f: (Int => Int)) = println(f)
		printlnFunc(addOne)

		val _f: (Int => Int) = addOne
		println(_f)
	}

	@Test def supplierLiteral: Unit = {
		object Taker {

			def takeFunctionLiteral(supplier: => Int): Unit = {
				println("taker takes")
				//				println(supplier.apply()) //can't compile
				println(supplier.getClass)
				println(supplier)
			}

			def takeExplicitFunction0(supplier: () => Int): Unit = {
				println("taker takes")
				println(supplier())
			}

		}

		val give5: () => Int = () => {
			println("giver gives")
			5
		}


//		println(give5.isInstanceOf[Function0[_]])

		Taker.takeFunctionLiteral({
			println("who gives")
			6
		})
		println()
		Taker.takeExplicitFunction0(give5)
	}

	@Test def functionCallByName: Unit = {
		object Taker {
			def take(supplier: => Int):Unit = println(supplier.getClass)
			def take1(supplier: () => Int):Unit = println(supplier.getClass)
		}

		Taker.getClass.getMethods.filter(_.getName.startsWith("take"))
		    .foreach(_.getParameters.foreach(println(_)))
	}

	@Test def functionLiteral1(): Unit = {
		import scala.collection.mutable._
		val map: Map[String, String] = Map.empty
		val op: () => String = () => {
			println("called")
			"value"
		}
		println("before")
		map.put("test", "no")
		val value: String = map.getOrElseUpdate("test", {
			println("called")
			"value"
		})
		println(op)
	}

	@Test def testCodeBlock(): Unit = {
		val getOne = {
			println(this)
			Thread.currentThread().getStackTrace.foreach(println);
			1
			2
		}
		println(getOne)
	}

	@Test def testFuncComposition {
		def f(s: String): String = s"f($s)"
		def g(s: String): String = s"g($s)"

		val f_g = f _ compose g _
		println(f_g)
		println(f_g("w"))

		val g_f = f _ andThen (g _)
		println(g_f)
		println(g_f("w"))
	}

	@Test def testPartialFunc: Unit = {
		def add(a: Int, b: Int): Int = a + b
		println(add(1, 2))

		val addOne = add(1, _: Int)
		println(addOne(5))

		val addThree2 = add(3, _: Int)

		def addThree1(a: Int): Int = a + 3
	}
}
