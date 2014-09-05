import scala.concurrent.Await
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._
 
implicit val timeout = Timeout(5 seconds)
val future = actor ? msg // enabled by the “ask” import
val result = Await.result(future, timeout.duration).asInstanceOf[String]
