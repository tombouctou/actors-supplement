// on machine 1: empty system, target for deployment from machine 2
val system = ActorSystem("MySystem")
 
// on machine 2: Remote Deployment - deploying on machine1
val system = ActorSystem("MySystem")
val greeter = system.actorOf(Props[GreetingActor], name = "greeter")
 
// on machine 3: Remote Lookup
// (logical home of “greeter” is machine2, remote deployment is transparent)
val system = ActorSystem("MySystem")
val addr = "akka.tcp://MySystem@machine2:2552/user/greeter"
val greeter = system.actorSelection(addr)
greeter ! Greeting("Sonny Rollins")
