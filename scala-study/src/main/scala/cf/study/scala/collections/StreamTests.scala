package cf.study.scala.collections

import org.junit.Test

/**
  * Created by fan on 2016/2/5.
  */
class StreamTests {
    @Test def testStream: Unit = {
        val intList: List[Int] = 1.to(10).toList
        val it: Iterator[Int] = intList.iterator
        Stream.continually(it.next).foreach(println)
    }

    @Test def testTakeWhile: Unit = {
        val intList: List[Int] = 1.to(10).toList
        val it: Iterator[Int] = intList.iterator
        Stream.continually(it.next).takeWhile(i => {
            println("before hasNext:" + i);
            it.hasNext
        }).foreach(println)
    }
}
