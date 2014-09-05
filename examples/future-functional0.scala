val f1 = Future {
  "Hello" + "World"
}
val f2 = f1 map { x => x.length }
f2 foreach println
