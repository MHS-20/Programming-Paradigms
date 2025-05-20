object Task4 extends App:
  enum Expr:
    case Literal(x: Int)
    case Add(x: Expr, y: Expr)
    case Multiply(x: Expr, y: Expr)

  object Expr:
    def evaluate(expr: Expr): Int = expr match
      case Literal(x) => x
      case Add(x, y) => evaluate(x) + evaluate(y)
      case Multiply(x, y) => evaluate(x) * evaluate(y)

    def show(expr: Expr): String = expr match
      case Literal(x) => x + ""
      case Add(x, y) => "(" + show(x) + "+" + show(y) + ")"
      case Multiply(x, y) => "(" + show(x) + "*" + show(y) + ")"

  import Expr.*

  val expr = Add(Literal(3), Multiply(Literal(4), Literal(5)))
  println("")
  println("===== TASK 4 - EXPR ======")
  println("Espressione: " + show(expr))
  println("Valore: " + evaluate(expr))