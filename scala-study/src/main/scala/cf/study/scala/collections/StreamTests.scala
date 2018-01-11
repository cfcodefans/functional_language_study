package cf.study.scala.collections

import org.junit.Test

import scala.collection.mutable.ListBuffer



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

    def classLoaderTree(clzLoader: ClassLoader): String = {
        import scala.collection.mutable.ListBuffer
        val cldList:ListBuffer[ClassLoader] = ListBuffer(clzLoader)
        while (cldList.last.getParent != null || cldList.size > 100) {
            cldList += cldList.last.getParent
        }
        cldList.mkString("\n\t")
    }

    @Test def testTakeWhile1: Unit = {
        println(classLoaderTree(Thread.currentThread().getContextClassLoader))
    }
}
