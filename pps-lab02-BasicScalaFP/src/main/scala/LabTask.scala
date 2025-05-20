
object LabTask extends App:

  // --- POSITIVE
  val pos1: Int => String = x => x match
    case x if x >= 0 => "positive"
    case _ => "negative"

  val pos2: Int => String = {
    case x if x >= 0 => "positive"
    case _ => "negative"
  }

  def positive(x: Int): String =
    x match
      case x if x >= 0 => "positive"
      case _ => "negative"

  // --- NEGATIVE
  val neg1: (String => Boolean) => (String => Boolean) = f => s => f(s) match
    case true => false
    case false => true

  def neg2(f: String => Boolean): String => Boolean =
    s => f(s) match
      case true => false
      case false => true

  def neg[A](f: A => Boolean): A => Boolean =
    a => f(a) match
      case true => false
      case false => true

  // --- Currying
  val p1: Int => Int => Int => Boolean = x => y => z =>
    x <= y && y == z

  val p2: (Int, Int, Int) => Boolean = (x, y, z) =>
    x <= y && y == z

  def p3(x: Int)(y: Int)(z: Int): Boolean =
    x <= y && y == z

  def p4(x: Int, y: Int, z: Int): Boolean =
    x <= y && y == z

  // --- Composition
  def compose(f: Int => Int, g: Int => Int): Int => Int = x => f(g(x))

  def composeGeneric[A, B, C](f: B => C, g: A => B): A => C = x => f(g(x))

  // --- Recursion
  def power(base: Double, exponent: Int): Double =
    @annotation.tailrec
    def _power(base: Double, exponent: Int, acc: Double): Double = exponent match
      case exponent if exponent > 0 => _power(base, exponent - 1, acc * base)
      case _ => acc

    _power(base, exponent, 1.0)

  def reverseNumber(n: Int): Int =
    def _reverse(n: Int, acc: Int): Int = n match
      case n if n != 0 => _reverse(n / 10, acc * 10 + n % 10)
      case _ => acc

    _reverse(n, 0)


  // Test POSITIVE
  println(s"pos1(5): ${LabTask.pos1(5)}") // positive
  println(s"pos1(-2): ${LabTask.pos1(-2)}") // negative
  println(s"pos2(0): ${LabTask.pos2(0)}") // positive
  println(s"positive(-1): ${LabTask.positive(-1)}") // negative

  // Test NEGATIVE
  val isHello: String => Boolean = _ == "hello"
  println(s"neg1(isHello)(\"hello\"): ${neg1(isHello)("hello")}") // false
  println(s"neg2(isHello)(\"world\"): ${neg2(isHello)("world")}") // true
  println(s"neg((x: Int) => x > 0)(-3): ${neg((x: Int) => x > 0)(-3)}") // true

  // Test CURRYING
  println(s"p1(1)(2)(2): ${p1(1)(2)(2)}") // true
  println(s"p2(1,2,2): ${p2(1, 2, 2)}") // true
  println(s"p1(3)(2)(2): ${p3(3)(2)(2)}") // false

  // Test COMPOSITION
  val plus1: Int => Int = _ + 1
  val times2: Int => Int = _ * 2
  println(s"compose(plus1, times2)(3): ${compose(plus1, times2)(3)}") // 7
  println(s"composeGeneric((x: String) => x.length, (y: Int) => y.toString)(1234): ${composeGeneric((x: String) => x.length, (y: Int) => y.toString)(1234)}") // 4

  // Test RECURSION
  println(s"power(2, 3): ${power(2, 3)}") // 8.0
  println(s"power(5, 0): ${power(5, 0)}") // 1.0
  println(s"reverseNumber(1234): ${reverseNumber(1234)}") // 4321
  println(s"reverseNumber(100): ${reverseNumber(100)}") // 1