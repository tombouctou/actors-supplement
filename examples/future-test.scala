val future = Future.successful("Yay!")

val otherFuture = Future.failed[String](new IllegalArgumentException("Bang!"))

val promise = Promise[String]()
val theFuture = promise.future
promise.success("hello")
