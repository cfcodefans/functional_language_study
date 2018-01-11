package cf.study.scala.lang

import java.time.temporal.{ChronoUnit, TemporalUnit}
import java.util.{Calendar, Date}

import org.junit.{Assert, Test}

/**
  * Created by fan on 2017/3/1.
  */
class RangeTest {

    @Test def testRange(): Unit = {
        for (i <- 1 to 1) println(i)
    }

    @Test def testRangeSlice(): Unit = {
        val values: List[Long] = MathTests.pi2Longs(20)

        val ranges: List[(Long, Long)] = values.take(values.size / 2)
            .zip(values.takeRight(values.size / 2).map(_ + 10))
            .map((r: (Long, Long)) => (Math.min(r._1, r._2), Math.max(r._1, r._2)))

        println(ranges.mkString(", "))

        val allRangeSlices: List[Array[(Long, Long)]] = ranges.map((r: (Long, Long)) => {
            val slices: Array[Long] = (r._1 to r._2 by 3).toArray
            val rangeSlices: Array[(Long, Long)] = slices.zipWithIndex
                .map((vi: (Long, Int)) => (vi._1, if (vi._2 < slices.length - 1) slices(vi._2 + 1) else r._2))
            rangeSlices
        })

        for (i <- 0 until ranges.length) {
            Assert.assertEquals(ranges(i)._2, allRangeSlices(i).last._2)
            println(s"${ranges(i)} -> ${allRangeSlices(i).mkString(", ")}")
        }
    }

    @Test def testDateRange(): Unit = {
        val start: Date = Calendar.getInstance().getTime
        (1 to 10).map(start.toInstant.plus(_, ChronoUnit.DAYS)).toArray
    }
}
