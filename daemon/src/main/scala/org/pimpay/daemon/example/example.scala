package org.pimpay.daemon.example

import java.lang.Thread
import scala.collection.mutable.{ HashMap ⇒ uHashMap }
import org.apache.commons.daemon._

trait ApplicationLifecycle {
  def start(): Unit
  def stop(): Unit
}

abstract class AbstractApplicationDaemon extends Daemon {
  def application: ApplicationLifecycle

  def init(daemonContext: DaemonContext) {}
  def start() = application.start()
  def stop() = application.stop()
  def destroy() = application.stop()
}

class ApplicationDaemon() extends AbstractApplicationDaemon {
  def application = new Application
}

object Main extends App {
  private[this] var cleanupAlreadyRun: Boolean = false

  override def main(args: Array[String]) {
  def createApplication() = new ApplicationDaemon
  val application = createApplication()

  def cleanup() {
    val previouslyRun = cleanupAlreadyRun
    cleanupAlreadyRun = true
    if (!previouslyRun) application.stop()
  }

  Runtime.getRuntime.addShutdownHook(new Thread(new Runnable {
    def run() {
      cleanup()
    }
  }))

  application.start()
  }
}
