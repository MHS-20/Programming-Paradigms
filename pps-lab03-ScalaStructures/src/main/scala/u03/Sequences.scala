package u03

import u03.Optionals.Optional
import u03.Optionals.Optional.*

object Sequences: // Essentially, generic linkedlists

  enum Sequence[E]:
    case Cons(head: E, tail: Sequence[E])
    case Nil()

  object Sequence:
    def sum(l: Sequence[Int]): Int = l match
      case Cons(h, t) => h + sum(t)
      case _ => 0

    def map[A, B](l: Sequence[A])(mapper: A => B): Sequence[B] = l match
      case Cons(h, t) => Cons(mapper(h), map(t)(mapper))
      case Nil() => Nil()

    def filter[A](l1: Sequence[A])(pred: A => Boolean): Sequence[A] = l1 match
      case Cons(h, t) if pred(h) => Cons(h, filter(t)(pred))
      case Cons(_, t) => filter(t)(pred)
      case Nil() => Nil()

    // Lab 03
    /*
     * Skip the first n elements of the sequence
     * E.g., [10, 20, 30], 2 => [30]
     * E.g., [10, 20, 30], 3 => []
     * E.g., [10, 20, 30], 0 => [10, 20, 30]
     * E.g., [], 2 => []
     */
    def skip[A](s: Sequence[A])(n: Int): Sequence[A] = s match
      case Cons(h, t) if n > 0 => skip(t)(n - 1)
      case _ => s

    /*
     * Zip two sequences
     * E.g., [10, 20, 30], [40, 50] => [(10, 40), (20, 50)]
     * E.g., [10], [] => []
     * E.g., [], [] => []
     */
    def zip[A, B](first: Sequence[A], second: Sequence[B]): Sequence[(A, B)] = (first, second) match
      case (Cons(h1, t1), Cons(h2, t2)) => Cons((h1, h2), zip(t1, t2))
      case _ => Nil()

    /*
     * Concatenate two sequences
     * E.g., [10, 20, 30], [40, 50] => [10, 20, 30, 40, 50]
     * E.g., [10], [] => [10]
     * E.g., [], [] => []
     */
    def concat[A](s1: Sequence[A], s2: Sequence[A]): Sequence[A] = s1 match
      case Cons(h, t) => Cons(h, concat(t, s2))
      case _ => s2

    /*
     * Reverse the sequence
     * E.g., [10, 20, 30] => [30, 20, 10]
     * E.g., [10] => [10]
     * E.g., [] => []
     */
    def reverse[A](s: Sequence[A]): Sequence[A] =
      def _rev(s: Sequence[A], acc: Sequence[A]): Sequence[A] = s match
        case Cons(h, t) => _rev(t, Cons(h, acc))
        case _ => acc

      _rev(s, Nil())


    /*
     * Map the elements of the sequence to a new sequence and flatten the result
     * E.g., [10, 20, 30], calling with mapper(v => [v, v + 1]) returns [10, 11, 20, 21, 30, 31]
     * E.g., [10, 20, 30], calling with mapper(v => [v]) returns [10, 20, 30]
     * E.g., [10, 20, 30], calling with mapper(v => Nil()) returns []
     */
    def flatMap[A, B](s: Sequence[A])(mapper: A => Sequence[B]): Sequence[B] = s match
      case Cons(h, t) => concat(mapper(h), flatMap(t)(mapper))
      case _ => Nil()

    /*
     * Get the minimum element in the sequence
     * E.g., [30, 20, 10] => 10
     * E.g., [10, 1, 30] => 1
     */
    def min(s: Sequence[Int]): Optional[Int] =
      def _min(s: Sequence[Int], min: Int): Optional[Int] = s match
        case Cons(h, t) if h < min => _min(t, h)
        case Cons(_, t) => _min(t, min)
        case _ => Optional.Just(min)

      _min(s, Int.MaxValue)

    /*
     * Get the elements at even indices
     * E.g., [10, 20, 30] => [10, 30]
     * E.g., [10, 20, 30, 40] => [10, 30]
     */
    def evenIndices[A](s: Sequence[A]): Sequence[A] =
      def _even(s: Sequence[A], index: Int): Sequence[A] = s match
        case Cons(h, t) if index % 2 == 0 => Cons(h, _even(t, index + 1))
        case Cons(_, t) => _even(t, index + 1)
        case _ => Nil()

      _even(s, 0)

    /*
     * Check if the sequence contains the element
     * E.g., [10, 20, 30] => true if elem is 20
     * E.g., [10, 20, 30] => false if elem is 40
     */
    def contains[A](s: Sequence[A])(elem: A): Boolean = s match
      case Cons(h, t) if h == elem => true
      case Cons(_, t) => contains(t)(elem)
      case Nil() => false

    /*
     * Remove duplicates from the sequence
     * E.g., [10, 20, 10, 30] => [10, 20, 30]
     * E.g., [10, 20, 30] => [10, 20, 30]
     */
    def distinct[A](s: Sequence[A]): Sequence[A] = s match
      case Cons(h, t) if contains(t)(h) => distinct(t)
      case Cons(h, t) => Cons(h, distinct(t))
      case Nil() => Nil()

    def distinctKeepingOrder[A](s: Sequence[A]): Sequence[A] =
      def _distinct(s: Sequence[A], duplicates: Sequence[A]): Sequence[A] = s match
        case Cons(h, t) if !contains(t)(h) => Cons(h, _distinct(t, duplicates))
        case Cons(h, t) if !contains(duplicates)(h) => Cons(h, _distinct(t, Cons(h, duplicates)))
        case Cons(_, t) => _distinct(t, duplicates)
        case Nil() => Nil()

      _distinct(s, Nil())

    /*
     * Group contiguous elements in the sequence
     * E.g., [10, 10, 20, 30] => [[10, 10], [20], [30]]
     * E.g., [10, 20, 30] => [[10], [20], [30]]
     * E.g., [10, 20, 20, 30] => [[10], [20, 20], [30]]
     */
    def group[A](s: Sequence[A]): Sequence[Sequence[A]] =
      def _grouping(s: Sequence[A], group: Sequence[A]): Sequence[Sequence[A]] = s match
        case Cons(h, t) if contains(group)(h) => Cons(Cons(h, Cons(h, Nil())), _grouping(t, group))
        case Cons(h, t) => Cons(Cons(h, Nil()), _grouping(t, Cons(h, Nil())))
        case Nil() => Nil()

      _grouping(s, Nil())

    /*
     * Partition the sequence into two sequences based on the predicate
     * E.g., [10, 20, 30] => ([10], [20, 30]) if pred is (_ < 20)
     * E.g., [11, 20, 31] => ([20], [11, 31]) if pred is (_ % 2 == 0)
     */
    def partition[A](s: Sequence[A])(pred: A => Boolean): (Sequence[A], Sequence[A]) =
      (filter(s)(pred), filter(s)(a => !pred(a)))

    def partitionRecursive[A](s: Sequence[A])(pred: A => Boolean): (Sequence[A], Sequence[A]) =
      def _part(s: Sequence[A], acc1: Sequence[A], acc2: Sequence[A])(pred: A => Boolean): (Sequence[A], Sequence[A]) = s match
        case Cons(h, t) if pred(h) => _part(t, Cons(h, acc1), acc2)(pred)
        case Cons(h, t) => _part(t, acc1, Cons(h, acc2))(pred)
        case _ => (acc1, acc2)

      _part(s, Nil(), Nil())(pred)

    def foldLeft[A, B](s: Sequence[A])(acc: B)(operator: (B, A) => B): B = s match
      case Cons(h, t) => foldLeft(t)(operator(acc, h))(operator)
      case _ => acc

  end Sequence
end Sequences

@main def trySequences =
  import Sequences.*
  val sequence = Sequence.Cons(10, Sequence.Cons(20, Sequence.Cons(30, Sequence.Nil())))
  println(Sequence.sum(sequence)) // 30
  import Sequence.*
  println(sum(map(filter(sequence)(_ >= 20))(_ + 1))) // 21+31 = 52
