class Child extends Actor {	
  var state = 0	
  def receive = {	
    case ex: Exception => throw ex	
    case x: Int        => state = x	
    case "get"         => sender ! state	
  }	
}
