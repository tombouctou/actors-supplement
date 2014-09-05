akka {
 actor {
   provider = akka.remote.RemoteActorRefProvider
   deployment {
     /greeter {
       remote = akka.tcp://MySystem@machine1:2552
     }
   }
 }
}
