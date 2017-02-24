package cf.study.scala.collections

import org.apache.commons.lang3.time.StopWatch
import org.apache.logging.log4j.{LogManager, Logger}
import org.junit.Test

import scala.collection.mutable

/**
  * Created by fan on 2017/2/21.
  */
object ScalaCollectionPrefTests {
    val log: Logger = LogManager.getLogger(ScalaCollectionPrefTests.getClass)
}

class ScalaCollectionPrefTests {

    import ScalaCollectionPrefTests._

    @Test
    def testListAdd(): Unit = {
        val sw: StopWatch = new StopWatch
        for (t <- 1 to 10) {
            sw.start()
            var list: List[Int] = List.empty[Int]
            for (i <- 1 to 500000) {
                list = i :: list
            }
            sw.stop()
            log.info(s"list::500000 ${sw.getTime}")
            sw.reset()
        }

        log.info("")

        for (t <- 1 to 10) {
            sw.start()
            var listBuf: mutable.ListBuffer[Int] = mutable.ListBuffer.empty[Int]
            for (i <- 1 to 500000) {
                listBuf += i
            }
            sw.stop()
            log.info(s"listBuf::500000 ${sw.getTime}")
            sw.reset()
        }
    }
}
