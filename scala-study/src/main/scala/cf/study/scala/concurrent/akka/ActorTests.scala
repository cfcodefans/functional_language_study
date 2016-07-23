package cf.study.scala.concurrent.akka

import java.util.concurrent.{Callable, TimeUnit}

import akka.actor.{Actor, ActorSystem, PoisonPill, Props}
import akka.dispatch.Futures
import org.junit.Test

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}


/**
  * Created by fan on 2016/2/11.
  */
class ActorTests {
	@throws[Exception]
	@Test def helloActor: Unit = {
		def longOper: Unit = {
			while (true) {
				try {
					val currentThreadName: String = Thread.currentThread.getName
					val currentThreadStatus: Boolean = Thread.interrupted
					println(s"$currentThreadName\t$currentThreadStatus")
					Thread.sleep(1000)
				} catch {
					case e: Throwable =>
						e.printStackTrace
				}
			}
		}

		class TestActor extends Actor {
			//			val log = Logging(context.system, this)
			override def receive: Receive = {
				case "test" => {
					longOper
				}
				case _ => println("received unknown message")
			}
		}

		val sys = ActorSystem("helloActor")
		val act1 = sys.actorOf(Props[TestActor](new TestActor), name = "act1")
		act1.tell("test", null)

		println("main thread: %s".format(Thread.currentThread))

		Thread.sleep(5000)
		act1.tell(PoisonPill.getInstance, null)
		//		sys.stop(act1)
		Thread.sleep(100)

		println("kill an actor")
		Thread.sleep(5000)

	}

	@Test
	def testFutures: Unit = {
		def c: Callable[String] = new Callable[String] {
			override def call(): String = {
				try {
					println(Thread.currentThread);
					return "done"
				} catch {
					case t: Throwable => {
						t.printStackTrace()
						return t.getMessage
					}
				}
			}
		}
		val fs: Future[String] = Futures.future(c, ExecutionContext.global)
		println(Await.result(fs, Duration(10, TimeUnit.SECONDS)))
//		fs onSuccess[Unit] ({
//			case msg => println(msg)
//		}) (ExecutionContext.global)
	}
}
