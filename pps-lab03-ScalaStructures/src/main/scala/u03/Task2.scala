package u03

object Task2 extends App:
  enum Person:
    case Student(name: String, year: Int)
    case Teacher(name: String, course: String)

  object Person:
    def name(p: Person): String = p match
      case Student(n, _) => n
      case Teacher(n, _) => n

    def isStudent(p: Person): Boolean = p match
      case Student(_, _) => true
      case _ => false

    def isTeacher(p: Person): Boolean = p match
      case Teacher(_, _) => true
      case _ => false

    def getCourse(p: Person): String = p match
      case Teacher(_, course) => course
      case _ => ""

    import u03.Sequences.*
    import Sequence.*

    def getCourses(s: Sequence[Person]): Sequence[String] =
      map(filter(s)(isTeacher))(getCourse)

    def coursesCount(s: Sequence[Person]): Int =
      foldLeft(map(filter(s)(isTeacher))(_ => 1))(0)(_ + _)





