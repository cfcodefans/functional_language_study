package cf.study.scala.tools.nsc.interpreter

import java.io.PrintWriter
import java.util.Date
import javax.script.ScriptContext

import org.junit.{After, Before, Test}

import scala.tools.nsc.Settings
import scala.tools.nsc.interpreter.IMain
import scala.collection.JavaConverters._

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
        im.eval("val a = 1")
        im.eval("println(a + a)")
    }

    @Test def testClass(): Unit = {
        val eval = im.eval("class TestClz(id:Long = System.currentTimeMillis()) {}")
        println(eval)

        var bindings = im.getBindings(ScriptContext.ENGINE_SCOPE)
        println(bindings.asScala.mkString("\n"))

        //NPE
//        bindings = im.getBindings(ScriptContext.GLOBAL_SCOPE)
//        println(bindings.asScala.mkString("\n"))

        im.eval("println(classOf[TestClz])")
    }

    @Test def testBind(): Unit = {
        im.directBind("now", new Date())
        im.eval("println(now)")
    }

    @Test def testEval(): Unit ={
        val eval = im.eval("new java.util.Date()")
        println(eval)
    }
}
