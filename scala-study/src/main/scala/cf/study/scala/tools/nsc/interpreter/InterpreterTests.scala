package cf.study.scala.tools.nsc.interpreter

import java.io.PrintWriter
import java.util.Date

import org.junit.{After, Before, Test}

import scala.collection.mutable
import scala.reflect.ClassTag
import scala.tools.nsc.Settings
import scala.tools.nsc.interpreter.IMain

/**
  * Created by fan on 2016/12/15.
  */
object InterpreterTests {

    def getIMain = {
        val s = new Settings()
        s.usejavacp.value = true
        val im: IMain = new IMain(s, new PrintWriter(System.out))
        im
    }
}

class InterpreterTests {

    import InterpreterTests._

    var im: IMain = _

    @Before def setup(): Unit = {
        im = getIMain
    }

    @After def tearDown(): Unit = {
        Option(im).map(_.close())
    }


    @Test def testPrintWriter(): Unit = {
        im.intp.interpret("val a = 1")
        im.intp.interpret("println(a + a)")
    }

    @Test def testClass(): Unit = {
        val eval = im.intp.interpret("class TestClz(id:Long = System.currentTimeMillis()) {}")
        println(eval)

//        var bindings = im.getBindings(ScriptContext.ENGINE_SCOPE)
        //        println(bindings.asScala.mkString("\n"))

        //NPE
        //        bindings = im.getBindings(ScriptContext.GLOBAL_SCOPE)
        //        println(bindings.asScala.mkString("\n"))

        im.intp.interpret("println(classOf[TestClz])")
    }

    @Test def testBind(): Unit = {
        im.directBind("now", new Date())
        im.intp.interpret("println(now)")
    }

    @Test def testEval(): Unit = {
        val eval = im.intp.interpret("new java.util.Date()")
        println(eval)
    }

    type Func[P] = (P, Date) => Any

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

        val eval = im.intp.interpret(script)
        val func: Func[Long] = eval.asInstanceOf[Func[Long]]
        func(100, new Date())

        callFunc[Long](100L, "test", script)
    }

    val cache: mutable.HashMap[String, (Func[_], Date)] = mutable.HashMap.empty
    def callFunc[P: ClassTag](param: P, scriptKey:String, script: String)(implicit paramType: Manifest[P]): Unit = {
        println(s"parameter:$param type is $paramType")

         val entry = cache.getOrElseUpdate(scriptKey, {(im.intp.interpret(script).asInstanceOf[Func[P]], new Date())})

        val eval = entry._1
        val func: Func[P] = eval.asInstanceOf[Func[P]]
        func(param, new Date())
    }
}
