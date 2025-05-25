package ex3

object Solitaire extends App:
  def render(solution: Seq[(Int, Int)], width: Int, height: Int): String =
    val reversed = solution.reverse
    val rows =
      for
        y <- 0 until height
        row = for
          x <- 0 until width
          number = reversed.indexOf((x, y)) + 1
        yield if number > 0 then "%-2d ".format(number) else "X  "
      yield row.mkString
    rows.mkString("\n")
  //println(render(solution = Seq((0, 0), (2, 1)), width = 3, height = 3))

  def placeMarks(width: Int, height: Int): Unit =
    val total = width * height
    val center = (width / 2, height / 2)
    val directions = List(
      (2, 0), (-2, 0), (0, 2), (0, -2),
      (1, 1), (-1, 1), (1, -1), (-1, -1)
    )

    def inBounds(x: Int, y: Int): Boolean =
      x >= 0 && x < width && y >= 0 && y < height

    def nextMoves(pos: (Int, Int), visited: Set[(Int, Int)]): Seq[(Int, Int)] =
      for
        (dx, dy) <- directions
        nx = pos._1 + dx
        ny = pos._2 + dy
        if inBounds(nx, ny)
        next = (nx, ny)
        if !visited.contains(next)
      yield next

    def search(path: Seq[(Int, Int)], visited: Set[(Int, Int)]): Unit =
      if path.size == total then
        println(render(path, width, height) + "\n")
      else
        for next <- nextMoves(path.last, visited) do
          search(path :+ next, visited + next)

import Solitaire.*
@main def runGameStreaming(): Unit =
  placeMarks(35, 35)
