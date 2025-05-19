package polyglot.a01a

import polyglot.a01a.Logics
import polyglot.a01a.Logics.Result

/** solution and descriptions at https://bitbucket.org/mviroli/oop2019-esami/src/master/a01a/sol2/ */
class LogicsImpl(private val size: Int, private val boat: Int) extends Logics:

  def hit(row: Int, col: Int) =
    Result.HIT
