package polyglot.a05b

import polyglot.a05b.Logics
import polyglot.a05b.Logics
import util.Sequences.*
import Sequence.*

import scala.util.Random

trait LogicTrait extends Logics: 
  def tick(): Unit
  def isOver: Boolean
  def hasElement(x: Int, y: Int): Boolean

/** solution and descriptions at https://bitbucket.org/mviroli/oop2019-esami/src/master/a05b/sol2/ */
class LogicsImpl(private val gridSize: Int) extends LogicTrait:
  opaque type Position = (Int, Int)
  private val random = new Random(69)
  private val initial: Position = (random.between(1, gridSize - 1), random.between(1, gridSize - 1))
  private var tickCount: Int = 0

  override def tick(): Unit = tickCount = tickCount + 1

  override def isOver: Boolean =
    val (ix, iy) = initial
    Seq(ix - tickCount, ix + tickCount, iy - tickCount, iy + tickCount)
      .exists(pos => pos < 0 || pos >= gridSize)

  override def hasElement(x: Int, y: Int): Boolean =
    val (ix, iy) = initial
    x >= 0 && x < gridSize && y >= 0 && y < gridSize &&
      (x - ix).abs + (y - iy).abs == tickCount
