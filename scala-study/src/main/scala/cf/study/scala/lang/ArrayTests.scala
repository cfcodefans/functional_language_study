package cf.study.scala.lang

import org.junit.Test

/**
  * Created by fan on 2016/9/22.
  */
class ArrayTests {
    @Test
    def testMultipleDimensions: Unit = {
        val ints: Array[Array[Int]] = Array.ofDim[Int](6, 6)
        for (i: Int <- 0.to(5)) {
            for (j: Int <- 0.to(5)) {
                print(ints(i)(j) + " ")
            }
            println
        }
    }

    @Test
    def testZipWithIndex(): Unit = {
        val a = (1 to 100).toArray
        a.zipWithIndex
    }

    @Test
    def testMax(): Unit = {
        val a = (1 to 100).toArray
        a.tail
    }

}
