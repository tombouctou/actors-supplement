class PartialFunctionBuilder[A, B] {
  import scala.collection.immutable.Vector
 
  // Abbreviate to make code fit
  type PF = PartialFunction[A, B]
 
  private var pfsOption: Option[Vector[PF]] = Some(Vector.empty)
 
  private def mapPfs[C](f: Vector[PF] ⇒ (Option[Vector[PF]], C)): C = {
    pfsOption.fold(throw new IllegalStateException("Already built"))(f) match {
      case (newPfsOption, result) ⇒ {
        pfsOption = newPfsOption
        result
      }
    }
  }
 
  def +=(pf: PF): Unit =
    mapPfs { case pfs ⇒ (Some(pfs :+ pf), ()) }
 
  def result(): PF =
    mapPfs { case pfs ⇒ (None, pfs.foldLeft[PF](Map.empty) { _ orElse _ }) }
}
 
trait ComposableActor extends Actor {
  protected lazy val receiveBuilder = new PartialFunctionBuilder[Any, Unit]
  final def receive = receiveBuilder.result()
}
 
trait TheirComposableActor extends ComposableActor {
  receiveBuilder += {
    case "foo" ⇒ sender ! "foo received"
  }
}
 
class MyComposableActor extends TheirComposableActor {
  receiveBuilder += {
    case "bar" ⇒ sender ! "bar received"
  }
}
