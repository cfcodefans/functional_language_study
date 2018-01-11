package cf.study.scala.lang

import org.junit.{Assert, Test}

/**
  * Created by fan on 2016/12/29.
  */
class MatchTests {

    @Test
    def testInstanceOf: Unit = {
        class Person() {println("Person is created")}
        class Male() extends Person {}
        class Man() extends Male {}
        class Boy() extends Male {}

        {
            val p: Person = new Man
            p match {
                case b: Boy => Assert.fail("can't be a boy")
                case m: Man => println("yes, a man")
                case m: Male => println("yes, a male")
                case p: Person => println("yes, a person")
            }
            if (true) return
        }

        {
            val p: Person = new Man
            p match {
                case b: Boy => Assert.fail("can't be a boy")
                case m: Male => println("yes, a male")
                case m: Man => println("yes, a man")
                case p: Person => println("yes, a person")
            }
        }
        {
            val p: Person = new Man
            p match {
                case b: Boy => Assert.fail("can't be a boy")
                case p: Person => println("yes, a person")
                case m: Man => println("yes, a man")
                case m: Male => println("yes, a male")
            }
        }

        {
            val p: Person = new Person // null
            p match {
                //null can't be used in match
                case b: Boy => Assert.fail("can't be a boy")
                case m: Man => println("yes, a man")
                case m: Male => println("yes, a male")
                case p: Person => println("yes, a person")
                case w: Any => println(s"who is $w")
            }
        }

        {
            println(null.isInstanceOf[Person])
        }
    }

    @Test def testCaseClassMatch: Unit = {
        class Person(gender: Int, age: Int)
        class Male(age: Int) extends Person(1, age)
        case class Man() extends Male(25)
        case class Boy() extends Male(15) {
            val p: Person = Man()
            p match {
                case Man() => println("yes, a man")
                case Boy() => println("yes, a boy")
                case _ => println("what else")
            }
        }

        {
            val p: Person = new Male(25)
            p match {
                case Man() => println("yes, a man")
                case _ => println("what else")
            }
        }
    }

    @Test def testExtractorMatch: Unit = {
        class Person(val gender: Int, val age: Int) {
            override def toString: String = Map("gender" -> gender, "age" -> age).toString()
        }
        class Male(age: Int) extends Person(1, age)
        case class Man() extends Male(25)
        case class Boy() extends Male(15)

        object Person {
            def apply(gender: Int, age: Int): Person = new Person(gender, age)

            def unapply(person: Person): Option[(Int, Int)] = {
                println(s"unapply $person")
                Option((person.gender, person.age))
            }
        }

        {
            val p: Person = Man()
            p match {
                case Person(1, 25) => println("yes, a man")
                case Person(1, 15) => println("yes, a boy")
                case _ => println("what else")
            }
        }

        {
            val p: Person = Boy()
            p match {
                case Person(1, 25) => println("yes, a man")
                case Person(1, 15) => println("yes, a boy")
                case _ => println("what else")
            }
        }

        {
            val p: Person = Person(0, 25)
            p match {
                case Person(1, 25) => println("yes, a man")
                case Person(1, 15) => println("yes, a boy")
                case Person(0, 25) => println("yes, a woman")
                case _ => println("what else")
            }
        }
    }
}
