val f1 = Future {
  "Hello" + "World"
}
val f2 = f1 map { x => x.length }
f2 foreach println

val f3 = f1 flatMap { x =>
  f2 map { y =>
    x.length * y
  }
}
// f3 :: [Future[Int]]
f3 foreach println
