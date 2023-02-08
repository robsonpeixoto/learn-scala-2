package essentials.lectures.part4pm

/** Created by Daniel.
  */
object PatternsEverywhere extends App {

  // big idea #1
  // catches are actually MATCHES
  val error =
    try {
      // code
      1 / 0
    } catch {
      case e: RuntimeException       => "runtime"
      case npe: NullPointerException => "npe"
      case _: Throwable              => "something else"
    }

  println(s"error => $error")

  // big idea #2
  val list = List(1, 2, 3, 4)
  val evenOnes = for {
    x <- list if x % 2 == 0 // ?!
  } yield 10 * x

  // generators are also based on PATTERN MATCHING
  val tuples = List((1, 2), (3, 4))
  val filterTuples = for {
    (first, second) <- tuples
  } yield first * second
  // case classes, :: operators, ...

  // big idea #3
  val tuple = (1, 2, 3)
  val (a, b, c) = tuple
  println(s"tuple => $a + $b + $c")
  // multiple value definitions based on PATTERN MATCHING
  // ALL THE POWER

  case class Hi(name: String)
  val say = Hi(name = "Bob")
  val Hi(name) = say
  println(s"the name is $name")

  val head :: tail = list
  println(s"head: $head")
  println(s"tail: $tail")

  // big idea #4 - NEW
  // partial function based on PATTERN MATCHING
  val mappedList = list.map {
    case v if v % 2 == 0 => v + " is even"
    case 1               => "the one"
    case _               => "something else"
  } // partial function literal

  val mappedList2 = list.map { x =>
    x match {
      case v if v % 2 == 0 => v + " is even"
      case 1               => "the one"
      case _               => "something else"
    }
  }
  println(mappedList)

}
