package cf.study.scala.concurrent.akka

import java.util.Date
import java.util.concurrent.{Callable, ExecutorService, Executors, TimeUnit}

import akka.actor.{Actor, ActorRef, ActorSystem, Kill, PoisonPill, Props}
import akka.dispatch.Futures
import akka.routing._
import org.apache.commons.lang3.time.DateFormatUtils
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

    class Inspector extends akka.actor.Actor {
        override def receive: akka.actor.Actor.Receive = {
            case (msg: Any) => {
                println(s"thread: ${Thread.currentThread()} received $msg\n")
                printTime
                Thread.currentThread().getStackTrace.foreach(println(_))
            }
        }
    }

    @Test
    def tryKillActor(): Unit = {
        val sys: ActorSystem = ActorSystem("Stack")
        val inspector: ActorRef = sys.actorOf(Props[Inspector](new Inspector), name = "inspector")
        inspector ! "this"
        inspector ! Kill
        inspector ! "what"
        Thread.sleep(100)
    }

    @Test
    def tryWait(): Unit = {
        class Dummy extends Actor {
            var ms: Long = System.currentTimeMillis()

            override def receive: Receive = {
                case (msg: Any) => {
                    val _ms: Long = System.currentTimeMillis()
                    if (_ms - ms > 999)
                        println(s"${_ms - ms}")
                    ms = _ms
                }
            }
        }
        val sys: ActorSystem = ActorSystem("Dummy")
        val ar: ActorRef = sys.actorOf(Props[Dummy](new Dummy))

        Future[Unit]({
            for (i <- 1 to 10) {
                Thread.sleep(1000)
                ar ! i
            }
        })(ExecutionContext.fromExecutorService(Executors.newSingleThreadExecutor()))

        Thread.sleep(10000);
    }

    @Test
    def exploreThread(): Unit = {
        val sys: ActorSystem = ActorSystem("Stack")
        val inspector: ActorRef = sys.actorOf(Props[Inspector](new Inspector), name = "inspector")
        inspector ! "this"
        Thread.sleep(100)
    }

    @Test
    def exploreRoute(): Unit = {
        for (t <- 1 to 10) {
            val sys: ActorSystem = ActorSystem("Route_" + t)
            val route: Router = {
                val routees = (1 to Runtime.getRuntime.availableProcessors()).map(i => {
                    sys.actorOf(Props[Inspector](new Inspector), name = s"inspector_$i")
                }).map(ActorRefRoutee(_)).toVector
                Router(RoundRobinRoutingLogic(), routees)
            }

            for (i <- 1 to 10) {
                route.route(i, ActorRef.noSender)
            }
        }

        Thread.sleep(100)
    }

    @Test
    def testBatchWithFSM(): Unit = {
        import akka.actor.FSM

        import scala.concurrent.duration._

        // received events
        final case class SetTarget(ref: ActorRef)
        final case class Queue(obj: Any)
        case object Flush

        //sent events
        final case class Batch(_batch: Seq[Any])

        //states
        sealed trait State
        case object Idle extends State
        case object Active extends State

        sealed trait Data
        case object Uninitialized extends Data
        final case class Todo(targte: ActorRef, queue: Seq[Any]) extends Data

        class Buncher extends FSM[State, Data] {
            startWith(Idle, Uninitialized)
            when(Idle) {
                case Event(SetTarget(ref), Uninitialized) => {
                    println(System.currentTimeMillis(), StateTimeout, Uninitialized, "stay idle")
                    stay().using(Todo(ref, Vector.empty))
                }
                case Event(ev: Any, d: Data) => {
                    println(ev, d, " go to Active")
                    goto(Active).using(d)
                }
            }

            when(Active, stateTimeout = 500 microsecond) {
                case Event(Flush | StateTimeout, t: Todo) => {
                    println(System.currentTimeMillis(), StateTimeout, t, "go to idle")
                    goto(Idle).using(t.copy(queue = Vector.empty))
                }
                case Event(ev: Any, d: Data) => {
                    println(System.currentTimeMillis(), ev, d, "stay active")
                    stay()
                }
            }

            onTransition({
                case (transition: (State, State)) => println(s"${System.currentTimeMillis()}:\t ${transition._1} => ${transition._2}")
            })

            initialize()
        }

        val sys: ActorSystem = ActorSystem("Buncher")
        val ar: ActorRef = sys.actorOf(Props[Buncher](new Buncher))

        Future[Unit]({
            Thread.sleep(1000)
            ar ! 1
            Thread.sleep(1000)
            ar ! 2
            Thread.sleep(100)
            ar ! 3
            ar ! 3
            ar ! 3
            ar ! 3
            ar ! 3
            ar ! 3
            ar ! 3
            ar ! 3
            ar ! 3
            ar ! 3
            ar ! 3
            ar ! 3
            Thread.sleep(1000)
            ar ! 4
            Thread.sleep(1000)
            ar ! 4
            Thread.sleep(1000)
            ar ! 4
        })(ExecutionContext.fromExecutorService(Executors.newSingleThreadExecutor()))

        Thread.sleep(10000);
    }

    @Test
    def tryStateWithFSM(): Unit = {
        import akka.actor.FSM

        import scala.concurrent.duration._

        sealed trait State
        case object Idle extends State
        case object Active extends State

        class StateActor extends FSM[State, Int] {
            startWith(Idle, 0, Option(1.second))

            onTransition({
                case (transition: (State, State)) => println(s"${System.currentTimeMillis()}:\t ${transition._1} => ${transition._2}")
            })

            whenUnhandled({
                case ev@Event(_ev: Any, i: Int) => {
                    println(s"when unhandled ev:\t $ev")
                    if (i < 0) goto(Idle)
                    else if (i > 0) goto(Active)
                    else stop()
                }
            })

            when(Idle, 500.millisecond) {
                case ev@Event(StateTimeout, i: Int) => {
                    println(s"timeout $ev happened, going from idle to idle")
                    goto(Idle)
                }
                case ev@Event(_ev: Int, i: Int) => {
                    println(s"when idle ev:\t $ev")
                    if (_ev < 0) stay()
                    else if (_ev > 0) {
                        println("going to be active")
                        goto(Active)
                    }
                    else stop()
                }
            }

            when(Active, 500.millisecond) {
                case Event(StateTimeout, i: Int) => {
                    println(s"timeout happened, going from active to idle")
                    goto(Idle)
                }
                case ev@Event(_ev: Int, i: Int) => {
                    println(s"when Active ev:\t $ev")
                    if (_ev < 0) stay()
                    else if (_ev > 0) goto(Active)
                    else stop()
                }
                case ev@_ => {
                    println(s"whatelse $ev")
                    stay()
                }
            }

            initialize()
        }

        val es: ExecutorService = Executors.newSingleThreadExecutor()
        val sys: ActorSystem = ActorSystem.create("StateActor",
            null,
            Thread.currentThread().getContextClassLoader,
            ExecutionContext.fromExecutorService(es))
        printTime
        val ar: ActorRef = sys.actorOf(Props[StateActor](new StateActor))
        Thread.sleep(1000)
        Thread.sleep(1000)
        ar ! 1
        Thread.sleep(1000)
        printTime
        es.shutdown()
        es.awaitTermination(100, SECONDS)
    }

    def printTime = {
        val millis: Long = System.currentTimeMillis()
        println(s"$millis\t${DateFormatUtils.format(millis: Long, "MM/dd/yyyy hh:mm:ss.sss")}")
    }

    @Test
    def testScheduler = {
        import scala.concurrent.duration._
        val as: ActorSystem = ActorSystem.create("Scheduler",
            null,
            Thread.currentThread().getContextClassLoader)

        val ar: ActorRef = as.actorOf(Props[Inspector](new Inspector))

        val cancellable = as.scheduler.schedule(1.second, 500.millisecond, ar, new Date())(ExecutionContext.global)
        printTime

        Thread.sleep(4000)
        cancellable.cancel()
    }
}
