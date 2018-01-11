package cf.study.scala.lang

import org.junit.Test

/**
  * Created by fan on 2017/2/20.
  */
class LazyTests {
    @Test def testYield: Unit ={
        lazy val c = for (i <- 0 to 2) yield {
            println(s"here $i")
            i
        }

        println("ready")
        for (i <- c) {
            println(i)
        }
    }

    @Test def testYieldStream: Unit ={
        lazy val c = for (i <- (0 to 2).toStream) yield {
            println(s"here $i")
            i
        }

        println("ready")
        for (i <- c) {
            println(i)
        }
    }

    @Test def testYieldStreamNext: Unit ={
        lazy val c = for (i <- (0 to 2).toStream) yield {
            println(s"here $i")
            i
        }

        println("ready")
        val it = c.iterator
        println(it.next())
        println(it.next())
    }
}
