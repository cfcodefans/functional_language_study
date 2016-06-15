package cf.study.scala.lang

import cf.study.scala.MiscTests
import cf.study.scala.oop.FieldAndMethodTests
import org.junit.{Assert, Test}

import scala.collection.mutable.ArrayBuffer

/**
 * Created by fan on 2015/11/12.
 */
class TraitsTests {
	trait Philosophical {
		def philosophize(): Unit = {
			println("I consume memory, therefore I am!")
		}
	}

	class Animal
	trait HasLegs {
		def legs = 4
	}
	trait HasWings extends HasLegs {
		def wings = 2
		override def legs = {
			2
		}
	}

	class Frog extends Animal with Philosophical with HasLegs {
		override def toString = "green"
	}

	class _Frog extends Animal with Philosophical with HasLegs {
		override def toString = "green"
		override def philosophize(): Unit = {
			println("It ain't easy being " + toString + "!")
		}
	}

	class Insect extends Animal with HasLegs with HasWings {
		override def legs:Int = {
			println("though super has %d legs".format(super.legs))
			return 6
		}
	}

	class Fly extends Insect with HasLegs with HasWings {
		override def wings = 4
//		override val legs = 6 //override a method by a val, in the end, it is compiled into java method
	}

	@Test def testFrog(): Unit = {
		val frog = new Frog
		println(frog)
		frog.philosophize
		Assert.assertTrue(frog.isInstanceOf[Philosophical])
		Assert.assertTrue(frog.isInstanceOf[HasLegs])
		Assert.assertTrue(frog.isInstanceOf[Animal])
		val animal = frog
		val _frog = animal.asInstanceOf[Frog]
		Assert.assertTrue(_frog.eq(frog))
		_frog.philosophize()

		val _f:Philosophical = new _Frog
		_f.philosophize()
		val __f:_Frog = _f.asInstanceOf[_Frog]
		println(__f.legs)

		val fly = new Fly
		println(fly.legs)
		val hasWings = fly
		println(hasWings.legs)
	}

	class Point(x: Int, y: Int)
	//trait NoPoint(x: Int, y: Int) //Does not compile

	// stackable modifications
	abstract class IntQueue {
		def get(): Int
		def put(x: Int)
	}

	class BasicIntQueue extends IntQueue {
		private val buf = new ArrayBuffer[Int]
		def get = buf.remove(0)
		def put(x: Int) = {buf += x}
	}

	trait Doubling extends IntQueue {
		//trait can extends class
		abstract override def put(x: Int) {
			println("Doubling.put(%d)".format(x))
			super.put(2 * x)
		}
	}

	class MyQueue extends BasicIntQueue with Doubling

	trait Incrementing extends IntQueue {
		abstract override def put(x: Int) {
			println("Incrementing.put(%d)".format(x))
			super.put(x + 1)
		}
	}

	trait Filtering extends IntQueue {
		abstract override def put(x: Int): Unit = {
			println("Filtering.put(%d)".format(x))
			if (x >= 0) super.put(x)
		}
	}

	@Test def testStackableMotification(): Unit = {
		val queue = new MyQueue
		queue.put(10)
		println(queue.get)

		{
			val stackQueue = (new BasicIntQueue with Incrementing with Filtering)
			stackQueue.put(2)
			println(stackQueue.get)
		}

		{
			val stackQueue = (new BasicIntQueue with Incrementing with Doubling) //Doubling's put is called first then the Incrementing's put method
			stackQueue.put(2)
			println(stackQueue.get)
		}

		{
			val stackQueue = (new BasicIntQueue with Doubling with Incrementing)
			stackQueue.put(2)
			println(stackQueue.get)
		}
	}

	trait IOperView extends Serializable {
		protected val name: String
		protected val result: Serializable
	}
	class Oper(_name: String, _result:Serializable = null) extends IOperView {
		val name: String = _name
		val result: Serializable = _result
	}

	@Test def testTraitWithProperties: Unit = {
		FieldAndMethodTests.printClass(classOf[IOperView])
		FieldAndMethodTests.printClass(classOf[Oper])
		val op = new Oper("test", 0.asInstanceOf[Serializable])
	}
}
