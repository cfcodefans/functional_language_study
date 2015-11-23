package cf.study.scala.oop

import org.junit.Test

/**
 * Created by fan on 2015/11/10.
 */
class Bar1(id: Int)

class Bar2(val id: Int)

class Bar3(_id: Int) {
	val id:Int = _id
}

class Bar4(_id: Int) {
	def id:Int = _id
}

class Bar5(var id:Int)

class Bar6 {
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

class FieldAndMethodTests {
	def printFields[T](cls: Class[T]) {
		cls.getDeclaredFields.map("\t" + _.toString).foreach(println)
	}
	def printContructors[T](cls: Class[T]): Unit = {
		cls.getConstructors.foreach(c => println("\t" + c))
	}

	def printMethods[T](cls: Class[T]): Unit = {
		cls.getDeclaredMethods.foreach(m => println("\t" + m))
	}

	def printClass[T](cls: Class[T]): Unit = {
		println("class " + cls.getSimpleName + " {")
		printContructors(cls)
		println()
		printFields(cls)
		println()
		printMethods(cls)
		println("}")
	}

	@Test def testFields(): Unit = {
		printClass(classOf[Bar1])
		printClass(classOf[Bar2])
		printClass(classOf[Bar3])
		printClass(classOf[Bar4])
		printClass(classOf[Bar5])
		printClass(classOf[Bar6])
	}
}
