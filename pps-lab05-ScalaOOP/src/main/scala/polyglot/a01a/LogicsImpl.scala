package polyglot.a01a

import polyglot.a01a.Logics
import polyglot.a01a.Logics.Result
import Result.*
import scala.util.Random

/** solution and descriptions at https://bitbucket.org/mviroli/oop2019-esami/src/master/a01a/sol2/ */

trait LogicTrait extends Logics:
  def hit(row: Int, col: Int): Result

class LogicsImpl(private val size: Int, private val boat: Int) extends LogicTrait:
  opaque type Position = (Int, Int)
  private val FAILURES = 5
  private val random = new Random(69)

  private var hit: Set[Position] = Set.empty
  private val boatRow = random.between(0, size)
  private val boatLeftCol = random.between(0, size - boat + 1)
  private var failures = 0

  override def hit(row: Int, col: Int): Result =
    if (row == this.boatRow && col >= this.boatLeftCol && col < this.boatLeftCol + boat)
      this.hit += (row, col)
      if (this.hit.size eq this.boat) Result.WON
      else Result.HIT
    this.failures = failures + 1
    if (this.failures eq FAILURES) Result.LOST
    else Result.MISS
