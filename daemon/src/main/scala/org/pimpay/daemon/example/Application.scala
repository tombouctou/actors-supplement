package org.pimpay.daemon.example

import akka.actor.{Props, ActorSystem}
import akka.io.IO
 import com.typesafe.scalalogging._
import org.apache.commons.daemon._
import org.pimpay.daemon.example.api.MyServiceActor
import spray.can.Http

class Application() extends ApplicationLifecycle with LazyLogging {
  private[this] var started: Boolean = false

  private val applicationName = "MyApplication"

  implicit val actorSystem = ActorSystem(s"$applicationName-system")

  def start() {
    logger.info(s"Starting $applicationName Service")

    if (!started) {
      started = true

      val myService = actorSystem.actorOf(Props[MyServiceActor], "my-service")

      IO(Http) ! Http.Bind(myService, interface = "0.0.0.0", port = 8280)
    }
  }

  def stop() {
    logger.info(s"Stopping $applicationName Service")

    if (started) {
      started = false
      actorSystem.shutdown()
    }
  }
}
