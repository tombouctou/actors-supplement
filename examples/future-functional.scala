val f1 = Future {
  "Hello" + "World"
}
val f2 = f1 map { x => x.length }
f2 foreach println

val f3 = f1 map { x =>
  f2 map { y =>
    x.length * y
  }
}
// f3 :: Future[Future[Int]]
f3 foreach println
