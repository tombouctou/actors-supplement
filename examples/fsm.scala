class Buncher extends Actor with FSM[State, Data] {
  startWith(Idle, Uninitialized)
  when(Idle) {
    case Event(SetTarget(ref), Uninitialized) =>
      stay using Todo(ref, Vector.empty)
  }
  // transition elided ...
  when(Active, stateTimeout = 1 second) {
    case Event(Flush | StateTimeout, t: Todo) =>
      goto(Idle) using t.copy(queue = Vector.empty)
  }
  // unhandled elided ...
  initialize()
}
