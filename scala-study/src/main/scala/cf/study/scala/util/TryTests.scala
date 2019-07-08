package cf.study.scala.util

import org.junit.Test

import scala.util.{Failure, Success, Try}


class TryTests {
    def break(s: String): String = throw new RuntimeException("Break")

    @Test
    def testTry(): Unit = {

        val _try: Try[String] = Try("what")
        val tryPrintln: Try[Unit] = _try.map(println)
        println(tryPrintln)

        tryPrintln match {
            case Success(v) =>
                println(s"what is v? $v")
            case Failure(e) =>
                e.printStackTrace()
        }

//        val tryBreak: Try[String] = _try.map(this.break(_))
//        println(tryBreak)
//
//        tryBreak match {
//            case Success(v) =>
//                println(s"what is v? $v")
//            case Failure(e) =>
//                e.printStackTrace()
//        }
    }
}
