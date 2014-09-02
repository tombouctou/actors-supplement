package org.pimpay.daemon.example.api

import akka.actor.{ Actor, ActorSystem }
import akka.event.Logging
import scala.concurrent.ExecutionContext
import spray.http._
import spray.routing._

class MyServiceActor extends Actor with MainRoute {
  val log = Logging(context.system, this)
  def actorRefFactory = context
  def receive = runRoute(route)
}

trait MainRoute extends HttpService {
  import ExecutionContext.Implicits.global

  val route =
    path("favicon.ico") {
      complete(StatusCodes.NotFound)
    } ~
    path("test") {
      complete("ok")
    }
}
