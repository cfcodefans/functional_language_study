package cf.study.scala.lang

import cf.study.scala.util.ClassLens
import org.junit.{Assert, Test}

/**
  * Created by fan on 2015/11/16.
  */
package object CaseClassTests {
	def foo(): Unit = {
		println("CaseClassTests.foo")
	}

	abstract class Expr {
		println("Expr constructor")
		def eval(s: String): String = s
	}

	case class Var(name: String) extends Expr {
		println("Var constructor: %s".format(name))
	}

	case class Number(num: Double) extends Expr {
		println("Number constructor: %f".format(num))
	}

	case class UnOp(operator: String, arg: Expr) extends Expr {
		println("new UnOp(%s, %s)".format(operator, arg))
	}

	case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

}

class Tests {

	import CaseClassTests._

	@Test def testCaseClass(): Unit = {
		//class cf.study.scala.lang.CaseClassTests.package$Var$
		println(Var.getClass)

		//class scala.runtime.AbstractFunction1
		println(Var.getClass.getSuperclass)

		val v = Var("x")
		val op = BinOp("+", Number(1), v)
		println(op)
		// unlike enum on java!!!
		Assert.assertTrue(op.left == Number(1))
		Assert.assertTrue(op.left equals Number(1))
		Assert.assertFalse(op.left eq Number(1))
	}

	@Test def testCopy(): Unit = {
		val v = Var("y")
		println(v.copy())
		val up = UnOp("|", v)
		println(up.copy(operator = "~"))
	}

	@Test def testPatternMatch(): Unit = {
		def simplifyTop(expr: Expr): Expr = expr match {
			// e is a placeholder here, altogether they form a pattern
			case UnOp("-", UnOp("-", e)) => e //Double negation
			case BinOp("+", e, Number(0)) => e //Adding zero
			case BinOp("*", e, Number(1)) => e //Multiplying by one
			case _ => expr
		}
	}

	@Test def testConstantPatterns(): Unit = {
		def describe(x: Any) = x match {
			case 5 => "five"
			case true => "truth"
			case "hello" => "hi"
			case Nil => "the empty list"
			case _ => "something else"
		}

		println(describe(5))
		println(describe(4))
		println(describe(null))
	}

	@Test def testVariablePatterns(): Unit = {
		import math.{E, Pi}
		def variablePatterns(x: Any) = x match {
			case Pi => "strange math? Pi = " + Pi
			case E => "E is " + E
			case _ => "OK"
		}
		println(variablePatterns(Pi))
	}

	@Test def testConstructorPatterns(): Unit = {
		println(Number(0) match {
			//be noted, the constructor of Number(1) is not called
			case Number(1) => println("found the one same as me")
			case _ =>
		})

		println(Number(1) match {
			//be noted, the constructor of Number(1) is not called
			case Number(1) => println("found the one same as me")
			case _ =>
		})
	}

	@Test def testSequencePattern(): Unit = {
		println(List(0, 2, 1) match {
			case List(0, _, _) => println("list begins with 0 and has 3 elements")
			case _ =>
		})

		println(List(0, 0, 1, 1, 2, 3) match {
			case List(0, _*) => println("list begins with 0")
			case _ =>
		})
	}

	@Test def testTuplePattern(): Unit = {
		val v: Any = 42
		println((v) match {
			case (a) => "(a)" //so it depends on order too
			case (42) => "answer of universe"
			case (a, b) => "(a, b)"
			case _ => "what"
		})

		println((v) match {
			//so it depends on order too
			case (42) => "answer of universe"
			case (a) => "(a)"
			case (a, b) => "(a, b)"
			case _ => "what"
		})

		println((42) match {
			case (42) => "answer of universe"
			case (a) => "(a)"
			case _ => "what"
		})
	}

	@Test def testTypedPattern(): Unit = {
		def len(x: Any) = x match {
			case s: String => s.length
			case m: Map[_, _] => m.size
			case a: Array[_] => a.length
			case _ => -1
		}

		println(len("abc"))
		println(len(Map(1 -> 'a', 2 -> 'b')))
		println(len(math.Pi))
	}

	@Test def testVarBinding(): Unit = {
		val n5: Number = Number(5)

		println()

		println(UnOp("abs", n5) match {
			case UnOp("abs", e@UnOp("abs", _)) => e
			case _ =>
		})

		println(UnOp("abs", UnOp("abs", n5)) match {
			// if you apply abs to a number multiple times
			// it would be same as you apply abs once
			case UnOp("abs", e@UnOp("abs", _)) => e
			case _ =>
		})

		println(UnOp("abs", UnOp("abs", UnOp("abs", n5))) match {
			// if you apply abs to a number multiple times
			// it would be same as you apply abs once
			case UnOp("abs", e@UnOp("abs", _)) => e
			case _ =>
		})
	}

	@Test def testSealClasses(): Unit = {
		{
			class TriOp(operator: String, a1: Expr, a2: Expr, a3: Expr) extends Expr {
				println("new TriOp(%s, %s, %s, %s)".format(operator, a1, a2, a3))
			}

			//synthesis methods are only generated while the class is defined within the case class
			//			val int = TriOp("Integration", Var("x"), Number(0), Number(10))
			val ing = new TriOp("Integration", Var("x"), Number(0), Number(10))
		}
	}

	def simplifyAll(expr: Expr): Expr = expr match {
		case UnOp("-", UnOp("-", e)) =>
			simplifyAll(e) //'-' is its own inverse
		case BinOp("+", e, Number(0)) =>
			simplifyAll(e) //'0' is a neutral element for '+'
		case BinOp("*", e, Number(1)) =>
			simplifyAll(e) //'1' is a neutral element for "*"
		case UnOp(op, e) =>
			UnOp(op, simplifyAll(e))
		case BinOp(op, l, r) =>
			BinOp(op, simplifyAll(l), simplifyAll(r))
		case _ => expr
	}

	@Test def testSimplifyAll(): Unit = {
		val n0: Number = Number(0)
		println(simplifyAll(UnOp("-", UnOp("-", UnOp("-", n0)))))
	}

	@Test def testCaseClassInspection: Unit = {
		println(ClassLens.prespective(Number.getClass))
		println(ClassLens.prespective(classOf[Number]))
	}
}
