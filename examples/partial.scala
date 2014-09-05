abstract class GenericActor extends Actor {
  def specificMessageHandler: Receive
  def genericMessageHandler:  Receive = {
    case event => printf("generic: %s\n", event)
  }
  def receive = specificMessageHandler orElse genericMessageHandler
}
 
class SpecificActor extends GenericActor {
  def specificMessageHandler = {
    case event: MyMsg => printf("specific: %s\n", event.subject)
  }
}
 
case class MyMsg(subject: String)
