import akka.actor._
class MyActor extends Actor {
  val log = Logging(context.system, this)
  // type Receive = PartialFunction[Any, Unit]
  def receive = { 
    case "test" => log.info("received test")
    case _      => log.info("received unknown message")
  }
}
object Main extends App {
  override def main(args: Array[String]) {
    val system  = ActorSystem()
    val testAct = system.actorOf(Props[MyActor])
    testAct ! "test"
    Thread.sleep(500) // 500ms
    system.shutdown
  }
}

