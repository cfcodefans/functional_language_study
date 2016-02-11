package cf.study.scala.oop

import java.lang.reflect.{Method, Modifier}

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

object FieldAndMethodTests {

	def getTypeStr[T](cls: Class[T]): String = {
		if (cls.isAnnotation) return "@interface"
		if (cls.isInterface) return "interface"
		if (cls.isEnum) return "enum"
		return "class"
	}

	def printFields[T](cls: Class[T]):Int = {
		cls.getDeclaredFields.map("\t" + _.toString).foreach(println)
		cls.getDeclaredFields.length
	}
	def printContructors[T](cls: Class[T]): Int = {
		cls.getConstructors.foreach(c => println("\t" + c))
		cls.getConstructors.length
	}

	def printMethods[T](cls: Class[T]): Unit = {
		val statics: Array[Method] = cls.getDeclaredMethods.filter(m => Modifier.isStatic(m.getModifiers))
		statics.foreach(m => println("\t" + m))
		if (!statics.isEmpty) println()
		cls.getDeclaredMethods.filter(m => !Modifier.isStatic(m.getModifiers)).foreach(m => println("\t" + m))
	}

	def printClass[T](cls: Class[T]): Unit = {
		println(getTypeStr(cls) + " " + cls.getSimpleName + " {")
		if (printContructors(cls) > 0)
			println()

		if (printFields(cls) > 0)
			println()

		printMethods(cls)
		println("}")
	}
}

class FieldAndMethodTests {
	import FieldAndMethodTests._
	@Test def testFields(): Unit = {
		printClass(classOf[Bar1])
		printClass(classOf[Bar2])
		printClass(classOf[Bar3])
		printClass(classOf[Bar4])
		printClass(classOf[Bar5])
		printClass(classOf[Bar6])
	}

	@Test def testObject: Unit = {
		printClass(classOf[FieldAndMethodTests])
	}
}
