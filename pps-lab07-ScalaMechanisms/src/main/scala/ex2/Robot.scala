package ex2

type Position = (Int, Int)
enum Direction:
  case North, East, South, West
  def turnRight: Direction = this match
    case Direction.North => Direction.East
    case Direction.East  => Direction.South
    case Direction.South => Direction.West
    case Direction.West  => Direction.North

  def turnLeft: Direction = this match
    case Direction.North => Direction.West
    case Direction.West  => Direction.South
    case Direction.South => Direction.East
    case Direction.East  => Direction.North

trait Robot:
  def position: Position
  def direction: Direction
  def turn(dir: Direction): Unit
  def act(): Unit

class SimpleRobot(var position: Position, var direction: Direction)
    extends Robot:
  def turn(dir: Direction): Unit = direction = dir
  def act(): Unit = position = direction match
    case Direction.North => (position._1, position._2 + 1)
    case Direction.East  => (position._1 + 1, position._2)
    case Direction.South => (position._1, position._2 - 1)
    case Direction.West  => (position._1 - 1, position._2)

  override def toString: String = s"robot at $position facing $direction"

class DumbRobot(val robot: Robot) extends Robot:
  export robot.{position, direction, act}
  override def turn(dir: Direction): Unit = {}
  override def toString: String = s"${robot.toString} (Dump)"

class LoggingRobot(val robot: Robot) extends Robot:
  export robot.{position, direction, turn}
  override def act(): Unit =
    robot.act()
    println(robot.toString)

class RobotWithBattery(val robot: Robot) extends Robot:
  var battery = 100
  export robot.{position, direction, turn}
  override def toString: String =
    s"${robot.toString} with battery $battery"
  override def act(): Unit =
    if battery != 0 then
      robot.act()
      battery -= 1
      println(this.toString)
    else println("low battery")

import scala.util.Random
class RobotCanFail(val robot: Robot) extends Robot:
  export robot.{position, direction, turn}
  val random: Random = new Random()
  override def act(): Unit =
    if random.nextBoolean() then robot.act()
    else println("robot failed to act")
  println(robot.toString)

class RobotRepeated(val robot: Robot, val repeat: Int) extends Robot:
  export robot.{position, direction, turn}
  override def act(): Unit =
    for _ <- 1 to repeat do robot.act()
  println(robot.toString)

@main def testRobot(): Unit =
  val robot = LoggingRobot(SimpleRobot((0, 0), Direction.North))
  robot.act() // robot at (0, 1) facing North
  robot.turn(robot.direction.turnRight) // robot at (0, 1) facing East
  robot.act() // robot at (1, 1) facing East
  robot.act() // robot at (2, 1) facing East

  val robot2 = RobotWithBattery(SimpleRobot((0, 0), Direction.North))
  robot2.act()
  robot2.turn(robot2.direction.turnRight)
  robot2.act()
  robot2.act()

  val robot3 = RobotCanFail(SimpleRobot((0, 0), Direction.North))
  robot3.act()
  robot3.turn(robot3.direction.turnRight)
  robot3.act()
  robot3.act()

  val robot4 = RobotRepeated(SimpleRobot((0, 0), Direction.North), 3)
  robot4.act()
  robot4.turn(robot3.direction.turnRight)
  robot4.act()
  robot4.act()
