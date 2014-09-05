val result = Future { loadPage(url) } andThen {
  case Failure(exception) => log(exception)
} andThen {
  case _ => watchSomeTV()
}
result foreach println
// -----------
val future4 = future1 fallbackTo future2 fallbackTo future3
future4 foreach println
// combine to tuple
val future3 = future1 zip future2 map { case (a, b) => a + " " + b }
future3 foreach println
