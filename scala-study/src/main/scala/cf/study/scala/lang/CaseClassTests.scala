package cf.study.scala.lang

import org.junit.{Assert, Test}

/**
 * Created by fan on 2015/11/16.
 */
package object CaseClassTests {
	abstract class Expr {
		println("Expr constructor")
	}
	case class Var(name: String) extends Expr {
		println("Var constructor")
	}
	case class Number(num: Double) extends  Expr
	case class UnOp(operator: String, arg: Expr) extends Expr
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
}
