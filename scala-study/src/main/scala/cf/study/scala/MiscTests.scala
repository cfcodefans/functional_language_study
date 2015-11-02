package cf.study.scala

import java.util.{Calendar, Date}

import cf.study.scala.MiscTests.MyClass
import org.junit.Test

import scala.actors.threadpool.AtomicInteger

/**
 * Created by fan on 2015/9/16.
 */

object  MiscTests {
	object MyClass {
		val ID_GEN:AtomicInteger = new AtomicInteger(0)
	}

	class MyClass(index: Int, _name: String) {
		val created:Date = Calendar.getInstance.getTime
		var updated:Date = Calendar.getInstance.getTime
		val id:Int = index
		val name:String = _name

		def this(_name: String) = this(MyClass.ID_GEN.getAndIncrement, _name)

		override def toString(): String = {
			"{id: %d, name: '%s', created: '%s', updated: '%s'}".format(index, name, created, updated)
		}
	}
}

class MiscTests {
	@Test def test1: Unit = {
		val _char:Character = new Character(26)
		println(_char)
	}

	@Test def testMyClass: Unit = {
		val one:MyClass = new MyClass(1, "one")
		println(one)
		println(one.created)
		println(one.updated)
		println(one.name)
		println(one.id)

		val _one:MyClass = new MyClass("_one")
		println(_one)
		println(_one.created)
		println(_one.updated)
		println(_one.name)
		println(_one.id)
	}
}
