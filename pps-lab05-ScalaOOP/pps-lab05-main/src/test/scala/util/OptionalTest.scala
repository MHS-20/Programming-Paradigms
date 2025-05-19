package util

import org.junit.*
import org.junit.Assert.*
import util.Optionals.Optional.*
class OptionalTest:
  @Test
  def testFlatMap(): Unit =
    val o1 = Just(1)
    assertEquals(o1.flatMap(x => Just(x + 1)), Just(2))
    assertEquals(Empty().flatMap(x => Just(x)), Empty())

  @Test
  def testMap(): Unit =
    val o1 = Just(1)
    assertEquals(o1.map(x => x + 1), Just(2))
    assertEquals(Empty().map(x => x), Empty())

  @Test
  def testOrElse(): Unit =
    val o1 = Just(1)
    assertEquals(o1.orElse(2), 1)
    assertEquals(Empty().orElse(2), 2)

  @Test
  def testFilter(): Unit =
    val o1 = Just(1)
    assertEquals(o1.filter(x => x == 1), Just(1))
    assertEquals(o1.filter(x => x == 2), Empty())
    assertEquals(Empty().filter(x => x == 1), Empty())