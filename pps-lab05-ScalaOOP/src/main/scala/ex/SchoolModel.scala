package ex

import util.Optionals.*
import util.Sequences.*
import Sequence.*
import ex.Course.CourseImpl

object SchoolModel {

  trait SchoolModule:
    type School
    type Teacher
    type Course

    def teacher(name: String): Teacher

    def course(name: String): Course

    def emptySchool: School

    extension (school: School)
      def courses: Sequence[String]
      def teachers: Sequence[String]
      def setTeacherToCourse(teacher: Teacher, course: Course): School
      def coursesOfATeacher(teacher: Teacher): Sequence[Course]
      def hasTeacher(name: String): Boolean
      def hasCourse(name: String): Boolean

  case class BasicSchoolModule() extends SchoolModule:
    override type School = Sequence[(Teacher, Course)]
    override type Teacher = String
    override type Course = String

    override def teacher(name: String): Teacher = name

    override def course(name: String): Course = name

    override def emptySchool: School = Sequence.empty

    extension (school: School)
      def courses: Sequence[String] = school.map(_._2)
      def teachers: Sequence[String] = school.map(_._1)
      def setTeacherToCourse(teacher: Teacher, course: Course): School = Cons((teacher, course), school)
      def coursesOfATeacher(teacher: Teacher): Sequence[Course] = school.filter(_._1 == teacher).map(_._2)
      def hasTeacher(name: String): Boolean = teachers contains name
      def hasCourse(name: String): Boolean = courses contains name

}