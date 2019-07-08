package cf.study.scala.util

import org.junit.Test

class EitherTests {
    @Test
    def testEither: Unit = {
        val et: Either[Int, Int] = Left(1)
        println(et)
    }
}
