import akka.actor.IndirectActorProducer
 
class DependencyInjector(applicationContext: AnyRef, beanName: String)
  extends IndirectActorProducer {
 
  override def actorClass = classOf[Actor]
  override def produce =
    // obtain fresh Actor instance from DI framework ...
}
 
val actorRef = system.actorOf(
  Props(classOf[DependencyInjector], applicationContext, "hello"),
  "helloBean")
