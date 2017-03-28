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

    @Test
    def testGrouped(): Unit = {
        val array: Array[Long] = MathTests.pi2Longs(20).toArray
        val groups: Iterator[Array[Long]] = array.grouped(5)
        println(groups.map(_.mkString(", ")).mkString("\n"))
    }

    @Test
    def testGroupBy(): Unit = {
        val array: Array[Long] = MathTests.pi2Longs(20).toArray
        println(array.mkString(", "))
        val groups: Map[Long, Array[Long]] = array.groupBy(e => e)
        println(groups.map(g => s"${g._1} ${g._2.length}").mkString("\n"))
        println(groups.toArray.map(g => (g._1, g._2.length)).groupBy(_._2).maxBy(_._1)._2.min._1)
    }

    object Solution {

        import java.util.Scanner

        def main(args: Array[String]) {
            val scan: Scanner = new Scanner(System.in)
            try {
                val n: Int = scan.nextInt
                val array: Array[Long] = Array.ofDim(n)
                for (i <- 0 until n) array(i) = scan.nextLong
                println("%.1f".format(array.sum.toFloat / array.length))

                val sorted = array.sorted
                println("%.1f".format({
                    val mid: Int = sorted.length / 2
                    if (sorted.length % 2 == 1) sorted(mid).toFloat else (sorted(mid - 1) + sorted(mid)) / 2f
                }))

                val groups: Map[Long, Array[Long]] = array.groupBy(e => e)
                println(groups.toArray.map(g => (g._1, g._2.length)).groupBy(_._2).maxBy(_._1)._2.min._1)
            } finally {
                scan.close
            }
        }
    }

}
