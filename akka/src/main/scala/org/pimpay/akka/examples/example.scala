package org.pimpay.akka.examples

import akka.actor.{ Actor, ActorSystem }
import akka.actor.Props
import akka.event.Logging
import akka.routing._
import java.lang.Thread
import scala.collection.mutable.{ HashMap ⇒ uHashMap }

class MyActor extends Actor {
  val log = Logging(context.system, this)

  def receive = {
    case "test" ⇒ log.info("received test")
    case _      ⇒ log.info("received unknown message")
  }
}

class ThreadActor extends Actor {
  val log = Logging(context.system, this)
  val threadId = Thread.currentThread().getId()

  def receive = {
    case _ ⇒ log.info(s"received at thread $threadId")
  }
}

class PingPongActor extends Actor {
  val log = Logging(context.system, this)

  def receive = {
    case msg @ "ping" ⇒ log.info(s"received $msg"); sender ! "pong"
    case msg @ "pong" ⇒ log.info(s"received $msg")
    case _      ⇒ log.info(s"received unknown msg")
  }
}

case class Count(
  val count:    Int,
  val threadId: Long
)

class OperatingActor extends Actor {
  val log = Logging(context.system, this)
  val threadId = Thread.currentThread().getId()
  var cnt = 0

  def receive = {
	  case _ ⇒ log.info(s"rcvd msg #$cnt on thread $threadId"); cnt = cnt + 1; sender ! Count(1, threadId)
  }
}

class CountingActor(max: Int) extends Actor {
  var count = 0
  var perThread: uHashMap[Long, Int] = uHashMap()
  val log = Logging(context.system, this)

  def receive = {
    case Count(cnt, threadId)
	  ⇒ count = count + cnt; log.info(s"got count msg, count=$count"); if (count == max) self ! "print"
    case "print"
	  ⇒ log.info(s"processed $count items"); if (count == max) println(perThread)
    case _ ⇒
  }
}

object Main extends App {
  override def main(args: Array[String]) {
  val system = ActorSystem()
  val testAct   = system.actorOf(Props[MyActor])
  val threadAct = system.actorOf(Props[ThreadActor])
  val pingAct   = system.actorOf(Props[PingPongActor])
  val max = 10
  val cntAct   = system.actorOf(Props(classOf[CountingActor], max))
  val operAct    = system.actorOf(Props(classOf[OperatingActor])
	  .withRouter(RoundRobinRouter(nrOfInstances = 5)))
  if (args.length > 0)
  {
    args(0) match {
      case "test"  ⇒ testAct   ! "test"
      case "thrd"  ⇒ threadAct ! "ololo"
      case "ping"  ⇒ pingAct.tell("ping", pingAct)
      case "count" ⇒ for(a <- 1 to max) operAct.tell("test", cntAct)
      case _ ⇒
    }
    Thread.sleep(500)
  }
  else
  {
    println("options: test|thrd|ping|count")
  }
  system.shutdown
  }
}
