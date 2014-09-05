import scala.concurrent.Future
import akka.pattern.ask
 
val future: Future[String] = ask(actor, msg).mapTo[String]

// -----------

import akka.pattern.pipe
future pipeTo actor
