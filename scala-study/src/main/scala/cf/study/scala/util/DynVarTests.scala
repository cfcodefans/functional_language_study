package cf.study.scala.util

import org.junit.Test

import scala.util.DynamicVariable

/**
 * Created by fan on 2016/1/19.
 */
class DynVarTests {

	@Test
	def example: Unit = {
		val dyn = new DynamicVariable[Int](0)

		println(dyn.value)

		new Thread(new Runnable {
			override def run(): Unit = {
				dyn.withValue(10) {
					val name = Thread.currentThread().getName
					println("%s:\t in dyn value is %s".format(name, dyn.value))
				}
			}
		}, "thread-1").start

		new Thread(new Runnable {
			override def run(): Unit = {
				dyn.withValue(20) {
					val name = Thread.currentThread().getName
					println("%s:\t in dyn value is %s".format(name, dyn.value))
				}
			}
		}, "thread-2").start

		Thread.sleep(1000)
	}
}
