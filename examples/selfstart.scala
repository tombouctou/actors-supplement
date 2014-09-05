import akka.actor._
class ScheduleInReceive extends Actor {
  import context._
  override def preStart() =
    system.scheduler.scheduleOnce(500 millis, self, "tick")
  // so we don't call preStart and schedule a new message
  override def postRestart(reason: Throwable) = {}
 
  def receive = {
    case "tick" =>
      system.scheduler.scheduleOnce(1000 millis, self, "tick")
      // do something useful here
  }
}
