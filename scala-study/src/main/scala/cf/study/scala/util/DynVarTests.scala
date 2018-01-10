package cf.study.scala.util

import java.util.Date
import java.util.concurrent.atomic.AtomicInteger

import org.junit.Test

import scala.util.DynamicVariable

/**
  * Created by fan on 2016/1/19.
  */
class DynVarTests {

    @Test
    def test: Unit = {
        val ID: AtomicInteger = new AtomicInteger(0)
        class Instant {
            val id: Int = ID.incrementAndGet()
            var date: Date = new Date
            println(s"${Thread.currentThread()}\t ${this.id}:\t$date")

            override def toString: String = s"{id:$id,\tdate:$date}"

            def clean: Unit = {
                date = null
                println("clean")
            }
        }

        val dyn: DynamicVariable[Option[Instant]] = new DynamicVariable[Option[Instant]](None)

        (1 to 100).toArray.par.foreach((i: Int) => {
            dyn.value match {
                case None => dyn.value_=(Some(new Instant))
            }
            dyn.value.foreach(println(_))
        })


    }

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
