package cf.study.scala.lang

import org.junit.Test

/**
  * Created by fan on 2017/6/27.
  */
class OptionTests {


    @Test
    def testOptionApply: Unit = {
        {
            val value: Any = null
            val opt: Option[Any] = Option(value)
            println(opt)
        }
        {
            val value: Any = None
            val opt: Option[Any] = Option(value)
            println(opt)
        }
    }
}
