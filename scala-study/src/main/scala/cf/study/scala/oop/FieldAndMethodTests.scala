package cf.study.scala.oop

import org.junit.Test

import scala.beans.BeanProperty

/**
  * Created by fan on 2015/11/10.
  */
class Bar1(id: Int)

class Bar2(private val id: Int)

class Bar3(_id: Int) {
    val id: Int = _id
}

class Bar4(_id: Int) {
    def id: Int = _id
}

class Bar5(var id: Int)

class Bar6(_id: Int) {
    var id: Int = _id;
}

class Bar7(_id: Int) {
    @BeanProperty var id: Int = _id
    @BeanProperty val age: Int = 0
}

class Bar8(@BeanProperty id: Int)

class _Bar {
    def public = println("public")

    implicit def _implicit = println("implicit")

    private def _private = println("private")

    protected def _protected = println("protected")
}

class Methods {
    def foo(i: Int): Int = i + 1

    def foo(f: Float): Float = f + 1

    def foo(): Int = 1

    def foo(i: Int*): Int = i.sum

    //	can't have same name
    //	def foo(i: Int): Float = i.toFloat

}

object FieldAndMethodTests {

    import cf.study.scala.util.ClassLens._

    def printClass[T](cls: Class[T]) = println(prespective(cls))
}

class FieldAndMethodTests {

    import FieldAndMethodTests._

    @Test def testFields(): Unit = {
        //		printClass(classOf[Bar1])
        printClass(classOf[Bar2])
        //		printClass(classOf[Bar3])
        //		printClass(classOf[Bar4])
        //		printClass(classOf[Bar5])
        //		printClass(classOf[Bar6])
        //		printClass(classOf[Bar7])
        //		printClass(classOf[Bar8])

        //		val b6 = new Bar6(5)
        //		b6.id = 3;
        //		println(b6.id)
    }

    @Test def testObject: Unit = {
        printClass(classOf[FieldAndMethodTests])
    }
}
