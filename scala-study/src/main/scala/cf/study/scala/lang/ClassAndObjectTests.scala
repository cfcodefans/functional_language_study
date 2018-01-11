package cf.study.scala.lang

import cf.study.scala.lang
import cf.study.scala.util.ClassLens
import org.junit.{Assert, Test}

import scala.collection.mutable.Map

/**
  * Created by fan on 2015/6/23.
  */

class ChecksumAccumulator {
    var sum = 0
    private var private_sum = 0

    def add(b: Byte): Unit = {
        //		b = 1
        //		This won't compile, because b is a val
        //		method parameters in scala are val, immutable
        private_sum += b
    }

    def checksum(): Int = {
        return ~(private_sum & 0xFF) + 1
    }
}

class ChecksumAccumulator_concise {
    private var sum = 0

    def add(b: Byte): Unit = sum += b

    def checksum(): Int = ~(sum & 0xFF) + 1
}

object ChecksumAccumulator {
    private val cache = Map[String, Int]()

    def calculate(s: String): Int = {
        if (cache.contains(s))
            cache(s)
        else {
            val acc = new ChecksumAccumulator
            for (c <- s)
                acc.add(c.toByte)
            val cs = acc.checksum()
            cache += (s -> cs)
            cs
        }
    }
}

object ObjMock {

}

class ClassAndObjectTests {

    @Test def classTest(): Unit = {
        new ChecksumAccumulator
        val acc = new ChecksumAccumulator
        val csa = new ChecksumAccumulator

        //literals refer to same address?
        Assert.assertTrue(acc.sum == csa.sum)

        acc.sum = 3
        Assert.assertFalse(acc.sum == csa.sum)

        csa.sum = 3
        Assert.assertTrue(acc.sum == csa.sum)

        //		Won't compile, because private_sum is private
        //		acc.private_sum = 5
    }

    @Test def convertToUnit() {
        def f(): Unit = "This String gets lost"

        println(f())
        println(f)

        def g() {
            //			println("println")
            print("print")
            return "this String gets lost too"
        }

        println(g())
        println(g)

        def h() = {
            "this String gets returned!"
        }

        println(h)
        println(h())
    }

    @Test def semicolonInference(): Unit = {
        val s = "hello";
        println(s)

        var x = 1
        if (x < 2)
            println("too small")
        else
            println("ok")

        var _x = x
        +x
        Assert.assertEquals(_x, x)
        _x = (x
            + x)
        Assert.assertEquals(_x, x + x)
    }

    @Test
    def testObject(): Unit = {
        println(ChecksumAccumulator.calculate("Every value is an object"))
    }

    @Test
    def testObjectAndClass = {
        println(ClassLens.prespective(Mock.getClass))
        println("==================================")
        println(ClassLens.prespective(classOf[Mock]))

        val mock = new Mock
        //		println(mock.object_Mock)
    }

    @Test
    def testThis = {
        println(ObjMock)
//        println(lang.ObjMock.this)
    }
}

object Mock {
    val object_Mock = "object Mock"
}

//illegal cyclic reference
class Mock extends {
    //Mock {
    val class_Mock = "class Mock"
}

