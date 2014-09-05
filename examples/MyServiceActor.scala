import akka.actor.{ Actor, ActorSystem }
// ...
class MyServiceActor extends Actor with MainRoute {
  def actorRefFactory = context
  def receive = runRoute(route)
}
class BasicActor extends Actor {
  def receive = {
    case HttpRequest(GET, Uri.Path("/ping"), _, _, _) =>
      sender ! HttpResponse(entity = "PONG")
  }
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
