package cf.study.scala.concurrent

import cf.study.scala.MiscTests
import org.junit.Test

import scala.concurrent.ExecutionContext._

/**
  * Created by fan on 2017/1/31.
  */
class ExecutionContextTests {
    @Test
    def testRunnable(): Unit = {
        global.prepare()
        global.execute(new Runnable {
            override def run() = {
                print(Thread.currentThread())
            }
        })
        Thread.sleep(10)
    }
}
