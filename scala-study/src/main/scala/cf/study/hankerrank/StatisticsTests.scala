package cf.study.hankerrank

import java.util.concurrent.ConcurrentLinkedQueue

import org.junit.Test

/**
  * Created by fan on 2017/3/29.
  */
class StatisticsTests {

    import java.util.Scanner

    def meanMedianMode(): Unit = {
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

    def weightedMean(): Unit = {
        import java.util.Scanner
        val scan: Scanner = new Scanner(System.in)
        try {
            val n: Int = scan.nextInt
            val X: Array[Int] = scan.nextLine().split(" ").map(_.toInt).toArray
            val W: Array[Int] = scan.nextLine().split(" ").map(_.toInt).toArray
            println("%.1f".format(X.zipAll(W, 0, 0).map((x_w) => x_w._1 * x_w._2).sum / n.toFloat))
        } finally {
            scan.close
        }
    }

    def median(array: Array[Int]): Float = {
        if (array.isEmpty) return Float.NaN
        val mid: Int = array.length / 2
        val sorted = array.sorted
        return if (sorted.length % 2 == 1) sorted(mid).toFloat else (sorted(mid - 1) + sorted(mid)) / 2f
    }

    def medianOfSorted(sorted: Array[Int]): Float = {
        if (sorted.isEmpty) return Float.NaN
        val mid: Int = sorted.length / 2
        return if (sorted.length % 2 == 1) sorted(mid).toFloat else (sorted(mid - 1) + sorted(mid)) / 2f
    }

    def quartilies(): Unit = {
        import java.util.Scanner
        val scan: Scanner = new Scanner(System.in)
        try {
            val n: Int = scan.nextInt
            val X: Array[Int] = scan.nextLine().split(" ").map(_.toInt).toArray
            val sorted: Array[Int] = X.sorted
            println(medianOfSorted(sorted.slice(0, n / 2)))
            println(medianOfSorted(sorted))
            println(medianOfSorted(sorted.slice(n / 2 + (n % 2), n)))
        } finally {
            scan.close()
        }
    }

    def interquartileRange(): Unit = {
        import java.util.Scanner
        val scan: Scanner = new Scanner(System.in)
        try {
            val n: Int = scan.nextInt
            scan.nextLine()
            val X: Array[Int] = scan.nextLine().split(" ").map(_.toInt).toArray
            val F: Array[Int] = scan.nextLine().split(" ").map(_.toInt).toArray
            val S: Array[Int] = X.zipAll(F, 0, 0).map(x_f => Array.fill(x_f._2)(x_f._1)).flatten.sorted
            println("%.1f".format(medianOfSorted(S.takeRight(S.length / 2)) - medianOfSorted(S.take(S.length / 2))))
        } finally {
            scan.close()
        }
    }

    def standardDeviation(): Unit = {
        import java.util.Scanner
        val scan: Scanner = new Scanner(System.in)
        try {
            val n: Int = scan.nextInt
            scan.nextLine()
            val X: Array[Int] = scan.nextLine().split(" ").map(_.toInt).toArray
            val mean: Double = X.sum / X.length.toDouble
            printf("%.1f\n", Math.sqrt(X.map(x => (x - mean) * (x - mean)).sum / X.length))
        } finally {
            scan.close()
        }
    }

    @Test def combinations(): Unit = {
        val list: List[Int] = 1 to 5 toList
        val r: Int = 3
        val buf: ConcurrentLinkedQueue[List[Int]] = new ConcurrentLinkedQueue[List[Int]]()

        def iterateForCombination(src: List[Int], combination: List[Int], c: Int): Unit = {
            if (c == 1) {
                src.foreach(e => buf.offer(e +: combination))
                return
            }

            val j: Int = src.size
            for (i <- 0 to j) {
                if (j - i <= c) {
                    buf.offer(combination ++ src.slice(i, j))
                    return
                }
                iterateForCombination(src.slice(i + 1, j), src(i) +: combination, c - 1)
            }
        }

        iterateForCombination(list, List.empty[Int], r)
        import collection.JavaConverters._
        buf.asScala.zipWithIndex.foreach(line => println(s"${line._2}\t ${line._1.mkString(", ")}"))
    }
}
