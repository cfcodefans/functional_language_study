package cf.study.scala.concurrent.akka

import java.util.concurrent.{Callable, TimeUnit}

import akka.actor.{Actor, ActorSystem, PoisonPill, Props}
import akka.dispatch.Futures
import akka.routing.RoundRobinPool
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

	@Test def sumExample = {
		sealed trait SumTrait
		case class Result(value: Int) extends SumTrait

		class SumActor extends Actor {
			val RANGE = 10000

			def calculate(start: Int, end: Int, flag: String): Int = {
				var cal = 0
				for (i <- (start to end)) {
					for (j <- 1 to 3000000) {}
					cal += 1
				}
				println(s"flag : ${flag}.")
				return cal
			}

			override def receive: Receive = {
				case value: Int =>
					sender() ! Result(calculate((RANGE / 4) * (value - 1) + 1, (RANGE / 4) * value, value.toString))
				case _ => println("unknown in SumActor...")
			}
		}

		class PrintActor extends Actor {
			override def receive: Receive = {
				case (sum: Int, startTime: Long) =>
					println(s"Sum: ${sum}; time spent: ${(System.nanoTime() - startTime) / 1000000000.0} seconds")
				case _ => println("unknown in PrintActor...")
			}
		}

		class MasterActor extends Actor {
			var sum = 0
			var count = 0
			var startTime: Long = 0

			val sumActor = context.actorOf(Props[SumActor](new SumActor).withRouter(RoundRobinPool(nrOfInstances = 4)), name = "sumActor")
			val printActor = context.actorOf(Props[PrintActor](new PrintActor), name = "printActor")

			override def receive: Receive = {
				case "calculate..." =>
					startTime = System.nanoTime
					for (i <- 1 to 4) sumActor ! i
				case Result(value) =>
					sum += value
					count += 1
					if (count == 4) {
						printActor ! (sum, startTime)
						context.stop(self)
					}
				case _ => println(s"unknown in MasterActor...")
			}
		}

		var sum = 0
		val system = ActorSystem("MasterActorSystem")
		val masterActor = system.actorOf(Props[MasterActor](new MasterActor), name = "masterActor")
		masterActor ! "calculate..."

		Thread.sleep(5000)
	}
}
