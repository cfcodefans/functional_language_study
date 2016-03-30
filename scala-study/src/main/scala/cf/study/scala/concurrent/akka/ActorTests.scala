package cf.study.scala.concurrent.akka

import akka.actor.{Actor, ActorSystem, PoisonPill, Props}
import akka.event.Logging
import org.junit.Test


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
}
