package study.spiral

class Spiral {
  def drawSpiral(matrix: Array[Array[String]]) {
    matrix.foreach(row => {
      row.foreach({
        case null => print(" ")
        case s => print(s)
      })
      println("")
    })
  }

  def spiral(stream: List[String], matrix: Array[Array[String]], position: (Int, Int), direction: Direction): Array[Array[String]] = {
    stream match {
      case Nil => matrix
      case head :: Nil => {
        matrix(position._1)(position._2) = head
        matrix
      }
      case "+" :: tail => {
        matrix(position._1)(position._2) = "+"
        val nextDirection = direction.next
        spiral(tail, matrix, position + nextDirection.difference, nextDirection)
      }
      case head :: tail => {
        matrix(position._1)(position._2) = head
        spiral(tail, matrix, position + direction.difference, direction)
      }
    }
  }

  def createSpriral(spiral: List[String], length: Int, direction: Direction): List[String] = {
    if (length == 0) spiral
    else {
      val newSpiral = spiral ::: goDirection(length, direction)
      createSpriral(newSpiral, length - 1, direction.next)
    }
  }

  def goDirection(length: Int, direction:Direction) = {
    (1 to length).map({
      case i if (i == length) => "+"
      case _ => direction.symbol
    }).toList
  }

  implicit class TuppleAdd(t: (Int, Int)) {
    def +(p: (Int, Int)) = (p._1 + t._1, p._2 + t._2)
  }
}
