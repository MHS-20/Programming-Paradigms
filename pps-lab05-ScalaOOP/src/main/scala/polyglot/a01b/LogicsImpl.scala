package polyglot.a01b

import polyglot.OptionToOptional
import util.Optionals.Optional as ScalaOptional
import polyglot.a01b.Logics
import scala.jdk.javaapi.OptionConverters
import scala.util.Random

trait LogicTrait extends Logics:
  def hit(x: Int, y: Int): java.util.Optional[Integer]
  def won: Boolean

/** solution and descriptions at https://bitbucket.org/mviroli/oop2019-esami/src/master/a01b/sol2/ */
class LogicsImpl(private val size: Int, private val mines: Int) extends LogicTrait:
  opaque type Position = (Int, Int)
  private val random = new Random(69)
  private var selectedSet: Set[Position] = Set.empty
  private val minesSet: Set[Position] =
    (for i <- 0 until mines yield
      val x = random.between(0, size)
      val y = random.between(0, size)
      (x, y)).toSet


  private def neighbours(x: Int, y: Int): Int =
    (for
      i <- x - 1 to x + 1
      j <- y - 1 to y + 1
      if minesSet.contains((i, j))
    yield (i, j)).size

  def hit(x: Int, y: Int): java.util.Optional[Integer] =
    if minesSet.contains((x, y)) then
      OptionToOptional(ScalaOptional.Empty())
    else
      selectedSet += (x, y)
      java.util.Optional.of(neighbours(x, y))

  def won =
    this.selectedSet.size + this.minesSet.size eq this.size * this.size
