package cf.study.hankerrank

import java.util._

import cf.study.hankerrank.SimpleTextEditor.{Append, Print, Removal, Undo}
import org.junit.Test

/**
  * Created by fan on 2016/11/12.
  */
object SimpleTextEditor {

	abstract class Oper[RT](val code: Int, val operand1: Any, val operand2: Any) {
		protected var result: RT = _

		def doOper(): RT

		def getUndo: Oper[RT] = null
	}

	val stack: Stack[Oper[String]] = new Stack[Oper[String]]

	case class Append(val appended: String, val appendage: String) extends Oper[String](1, appended, appendage) {
		override def doOper(): String = {
			result = String.valueOf(appended) + String.valueOf(appendage)
			stack.push(this)
			return result
		}

		override def getUndo: Oper[String] = Removal(result, appendage.length)
	}

	case class Removal(val removed: String, val num: Int) extends Oper[String](2, removed, num) {
		override def doOper(): String = {
			result = removed.take(Math.max(removed.length - num, 0))
			stack.push(this)
			return result
		}

		override def getUndo: Oper[String] = Append(result, removed.takeRight(num))
	}

	case class Print(val str: String, val index: Int) extends Oper[String](3, str, index) {
		override def doOper(): String = {
			println(str(index - 1))
			return str
		}
	}

	case class Undo(val str: String) extends Oper[String](4, str, null) {
		override def doOper(): String = {
			if (stack.empty()) return str
			val lastOper: Oper[String] = stack.pop()
			val re: String = lastOper.getUndo.doOper()
			stack.pop()
			return re
		}
	}

	def withScanner(sc: Scanner): Unit = {
		val q: Int = sc.nextInt
		var str: String = ""
		for (i <- 1 to q) {
			val code: Int = sc.nextInt
			code match {
				case 1 => {
					str = Append(str, sc.next).doOper()
				}
				case 2 => {
					str = Removal(str, sc.nextInt).doOper()
				}
				case 3 => {
					str = Print(str, sc.nextInt).doOper()
				}
				case 4 => {
					str = Undo(str).doOper()
				}
			}
		}
	}
}

class SimpleTextEditor {
	import SimpleTextEditor._
	@Test def test1: Unit = {
		val input:String =
			"""|10
			  |1 ewcgpjfh
			  |1 igqsbqyp
			  |1 qsdliigcj
			  |4
			  |3 15
			  |1 iilmgp
			  |2 8
			  |4
			  |2 18
			  |1 scwhors
			""".stripMargin
		val sc: Scanner = new Scanner(input)
		try {
			withScanner(sc)
		} finally {
			sc.close
		}
	}

	@Test def test: Unit = {
		var result: String = Append("", "abc").doOper()
		result = Print(result, 3).doOper()
		//		println("\t", result)
		result = Removal(result, 3).doOper()
		//		println("\t", result)
		result = Append(result, "xy").doOper()
		//		println("\t", result)
		result = Print(result, 2).doOper()
		//		println("\t", result)
		result = Undo(result).doOper()
		//		println("\t", result)
		result = Undo(result).doOper()
		//		println("\t", result)
		result = Print(result, 1).doOper()
		//		println("\t", result)
	}


}
