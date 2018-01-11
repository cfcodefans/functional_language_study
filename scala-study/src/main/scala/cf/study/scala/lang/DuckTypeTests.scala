package cf.study.scala.lang

import org.junit.Test

/**
  * Created by fan on 2017/7/7.
  */
class DuckTypeTests {
    @Test
    def testDuckTypes(): Unit = {
        def show(obj: {val name: String}): Unit = println(s"type:\t${obj} \n\tname is ${obj.name}")

        show(new {
            val name = "unknown"
        })

        class Person(val name: String)
        class Currency(val name: String)
        case class Season(val name: String)

        show(new Person("fan"))
        show(new Currency("usd"))
        show(Season("winter"))
    }
}
