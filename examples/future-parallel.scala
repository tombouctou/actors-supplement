import scala.concurrent.Await
import scala.concurrent.Future
import scala.concurrent.duration._
 
val future = Future {
  "Hello" + "World"
}
future foreach println
