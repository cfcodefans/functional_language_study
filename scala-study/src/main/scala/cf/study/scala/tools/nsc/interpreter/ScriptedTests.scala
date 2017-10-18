package cf.study.scala.tools.nsc.interpreter

import java.util.Date

import org.junit.Test

import scala.collection.mutable
import scala.reflect.ClassTag
import scala.tools.nsc.interpreter.Scripted

object ScriptedTests {
    val se: Scripted = Scripted()
    type Func[P] = (P, Date) => Any
}

class ScriptedTests {

    import ScriptedTests._

    @Test
    def testEval(): Unit = {
        var re: AnyRef = se.eval("val a = 1")
        println(re)
        println(s"context[a] is ${se.getContext.getAttribute("a")}")
        //        println(s"engineBindings[a] is ${se.getBindings(ScriptContext.ENGINE_SCOPE).get("a")}")
        //        println(s"globalBindings[a] is ${se.getBindings(ScriptContext.GLOBAL_SCOPE).get("a")}")

        //        println(se.dynamicContext.getAttribute("a"))
        println(se.get("a"))

        re = se.eval("a + 1")
        println(re)
    }

    @Test
    def testTyping(): Unit = {

        //        class Dummy extends Func[Long] {
        //            override def apply(v1: Long, v2: Date) = println(s"$v1 at $v2")
        //        }

        val script: String =
            """
              |import java.util._
              |class Script extends Function2[Long, Date, Any] {
              |    override def apply(v1: Long, v2: Date) = println(s"$v1 at $v2")
              | }
              | new Script
            """.stripMargin

        val eval = se.eval(script)
        val func: Func[Long] = eval.asInstanceOf[Func[Long]]
        func(100, new Date())

        callFunc[Long](100L, "test", script)
        callFunc[Long](100L, "test", script)
        callFunc[Long](100L, "test", script)
    }

    val cache: mutable.HashMap[String, (Func[_], Date)] = mutable.HashMap.empty

    def callFunc[P: ClassTag](param: P, scriptKey: String, script: String)(implicit paramType: Manifest[P]): Unit = {
        println(s"parameter:$param type is $paramType")

        val entry = cache.getOrElseUpdate(scriptKey, {
            (se.eval(script).asInstanceOf[Func[P]], new Date())
        })

        val eval = entry._1
        val func: Func[P] = eval.asInstanceOf[Func[P]]
        func(param, new Date())
    }
}
