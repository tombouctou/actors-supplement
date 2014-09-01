package org.pimpay.akka.examples

import akka.actor.Actor
import akka.actor.Props
import akka.event.Logging

class MyActor extends Actor {
  val log = Logging(context.system, this)
  def receive = {
    case "test" ⇒ log.info("received test")
    case _      ⇒ log.info("received unknown message")
  }
}

object Main extends App {
  override def main(args: Array[String]) {
    println("Hello, world! " + args.toList)
  }
}
