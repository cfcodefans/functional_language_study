package cf.study.scala.lang

import org.apache.commons.lang3.StringUtils
import org.apache.commons.lang3.math.NumberUtils
import org.junit.{AfterClass, BeforeClass, Test}

/**
  * Created by fan on 2016/2/8.
  */

object StringTests {
    @BeforeClass def init = {
        println("BeforeClass")
    }

    @AfterClass def clean = {
        println("AfterClass")
    }


}

class StringTests {
    @Test def testSplit: Unit = {
        println("abc".split(',').toList)
        println("/data".split(',').toList)
        println("\u000A")

        val s: String = "abc"
        //		s.substring(s.length-2)
    }

    @Test def getOffsets: Unit = {
        var offsetProp: String = "((event-message,1,1904994), (event-message,0,1953955), (event-message,2,1924272))"

        if (StringUtils.isBlank(offsetProp)) {
            return Set.empty
        }

        offsetProp = StringUtils.stripEnd(StringUtils.stripStart(offsetProp, "("), ")").trim
        val toSet = offsetProp.split("\\)\\, *\\(")
            .map(_.trim)
            .map(each => StringUtils.stripEnd(StringUtils.stripStart(each, "("), ")"))
            .filter(StringUtils.isNotBlank(_))
            .map(_.split(','))
            .filter(vals => vals.length == 3 && NumberUtils.isNumber(vals(1)) && NumberUtils.isNumber(vals(2)))
            .map(vals => (vals(0), vals(1).toInt, vals(2).toLong))
            .distinct.toSet

        println(toSet.mkString("\t"))
    }

    @Test def testStringInterpolation: Unit = {
        def getStack: String = Thread.currentThread.getStackTrace.head.toString

        println(s"where is the invocation? \n\t${getStack}")

        def examStringCtx(sc: StringContext): Unit = {
            println(sc.parts.size)
            println(sc.parts.mkString("\t###\t"))
        }

        val sc: StringContext = StringContext("where is the invocation? ${getStack}", "end")
        examStringCtx(sc)
        println(sc.s(getStack))
    }

    @Test
    def testFormat(): Unit = {
		println("%.1f".format(Math.PI))
    }
}
