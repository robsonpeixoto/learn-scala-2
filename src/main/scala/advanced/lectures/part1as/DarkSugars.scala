package advanced.lectures.part1as

import scala.util.Try

/** Created by Daniel.
  */
object DarkSugars extends App {

  // syntax sugar #1: methods with single param
  def singleArgMethod(arg: Int): String = s"$arg little ducks..."

  val description = singleArgMethod {
    // write some complex code
    42
  }

  println(s"description $description")

  def singleArgMethodByName(arg: => Int): String = {
    println("hi")
    val r = s"$arg little ducks..."
    println("end")
    r
  }
  val descriptionByName = singleArgMethodByName {
    println("byName")
    20
  }
  println(s"descriptionByName $descriptionByName")

  val aTryInstance = Try { // java's try {...}
    throw new RuntimeException
  }

  List(1, 2, 3).map { x =>
    x + 1
  }

  // syntax sugar #2: single abstract method
  trait Action {
    def act(x: Int): Int
  }

  val anInstance: Action = new Action {
    override def act(x: Int): Int = x + 1
  }

  val aFunkyInstance: Action = (x: Int) => x + 1 // magic

  // example: Runnables
  val aThread = new Thread(new Runnable {
    override def run(): Unit = println("hello, Scala")
  })

  val aSweeterThread = new Thread(() => println("sweet, Scala!"))

  val aSweeterRunnable: Runnable = () => println("sweet runnable")

  abstract class AnAbstractType {
    def implemented: Int = 23
    def f(a: Int): Unit
  }

  val anAbstractInstance: AnAbstractType = (a: Int) => println("sweet")

  // syntax sugar #3: the :: and #:: methods are special

  val prependedList = 2 :: List(3, 4)
  // 2.::(List(3,4))
  // List(3,4).::(2)
  // ?!

  // scala spec: last char decides associativity of method
  1 :: 2 :: 3 :: List(4, 5)
  List(4, 5).::(3).::(2).::(1) // equivalent

  class MyStream[T] private (elements: List[T]) {
    def this() = this(List[T]())
    def -->:(value: T): MyStream[T] =
      new MyStream[T](value :: elements)
    override def toString(): String = this.elements.toString
  }

  // It works because the method finish with `:`
  val myStream = 1 -->: 2 -->: 3 -->: new MyStream[Int]
  println(myStream)

  // syntax sugar #4: multi-word method naming

  class TeenGirl(name: String) {
    def `and then said`(gossip: String) = println(s"$name said $gossip")
  }

  val lilly = new TeenGirl("Lilly")
  lilly `and then said` "Scala is so sweet!"

  // syntax sugar #5: infix types
  class Composite[A, B]
  val composite: Int Composite String = new Composite[Int, String]

  class -->[A, B]
  val towards: Int --> String = new -->[Int, String]

  // syntax sugar #6: update() is very special, much like apply()
  val anArray = Array(1, 2, 3)
  println(s"Array before $anArray")
  anArray(2) = 7 // rewritten to anArray.update(2, 7)
  println(s"Array after $anArray")
  // used in mutable collections
  // remember apply() AND update()!

  // syntax sugar #7: setters for mutable containers
  class Mutable {
    private var internalMember: Int = 0 // private for OO encapsulation
    def member = internalMember // "getter"
    def member_=(value: Int): Unit =
      internalMember = value // "setter"
  }

  val aMutableContainer = new Mutable
  println(s"Mutable before ${aMutableContainer.member}")
  aMutableContainer.member = 42 // rewrittern as aMutableContainer.member_=(42)
  println(s"Mutable after ${aMutableContainer.member}")

}
