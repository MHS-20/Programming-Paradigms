package u04lab

import u03.Sequences.*
import Sequence.*
import u03.Optionals.*
import Optional.*

/*  Exercise 5: 
 *  - Generalise by ad-hoc polymorphism logAll, such that:
 *  -- it can be called on Sequences but also on Optional, or others... 
 *  -- it does not necessarily call log, but any function with analogous type
 *  - Hint: introduce a type class Traversable[T[_]], capturing the ability of calling a
 *    "consumer function" on all elements (with type A) of a datastructure T[A] 
 *    Note Traversable is a 2-kinded trait (similar to Filterable, or Monad)
 *  - Write givens for Traversable[Optional] and Traversable[Sequence]
 *  - Show you can use the generalisation of logAll to:
 *  -- log all elements of an Optional, or of a Traversable
 *  -- println(_) all elements of an Optional, or of a Traversable
 */

object Ex5Traversable:
  def log[A](a: A): Unit = println("The next element is: " + a)

  def logAll[A](seq: Sequence[A]): Unit = seq match
    case Cons(h, t) => log(h); logAll(t)
    case _ => ()

  def logAll[T[_] : Traversable, A](ta: T[A]): Unit =
    summon[Traversable[T]].traverse(ta)(log)

  trait Traversable[T[_]]:
    def traverse[A](t: T[A])(f: A => Unit): Unit

  given Traversable[Optional] with
    def traverse[A](t: Optional[A])(f: A => Unit): Unit = t match
      case Just(a) => f(a)
      case _ => ()

  given Traversable[Sequence] with
    def traverse[A](t: Sequence[A])(f: A => Unit): Unit = t match
      case Cons(h, t) => f(h); traverse(t)(f)
      case _ => ()

  @main def traversableDemo(): Unit =
    import u03.Sequences.*
    import u03.Optionals.*

    val seq1 = Cons(1, Cons(2, Cons(3, Nil())))
    val seq2 = Cons("a", Cons("b", Cons("c", Nil())))
    val opt = Just(42)
    val emptyOpt = Empty()

    logAll(seq1)
    logAll(seq2)

    logAll(emptyOpt)
    summon[Traversable[Sequence]].traverse(seq1)(a => println(s"Elemento: $a"))

    println("Stampa personalizzata su Optional:")
    summon[Traversable[Optional]].traverse(opt)(a => println(s"Valore opzionale: $a"))

    val seqOpt = Cons(Just(1), Cons(Empty(), Cons(Just(3), Nil())))
    logAll(seqOpt)