package study.spiral


class Spiral {

  def draw(row: Array[String]): Unit = {
    row.foreach({
      case null => print(" ")
      case s => print(s)
    })
    println("")
  }

  def drawSpiral(matrix: Array[Array[String]]) {
    matrix.foreach(row => draw(row))
  }

  def spiralStreamToMatrix(stream: List[String], direction: Direction, length: Int): Array[Array[String]] = {
    val matrix =  Array.ofDim[String](length, length)
    var d = direction
    var s = (0, 0)
    stream.foldLeft(matrix)((m, symbol) => symbol match {
      case "+" => {
        matrix(s._1)(s._2) = symbol
        d = d.next()
        s = s + d.difference()
        matrix
      }
      case _ => {
        matrix(s._1)(s._2) = symbol
        s = s + d.difference()
        matrix
      }
    })
    matrix
  }

  def spiral(stream: List[String], matrix: Array[Array[String]], position: (Int, Int), direction: Direction): Array[Array[String]] = {
    stream match {
      case head :: Nil => {
        matrix(position._1)(position._2) = head
        matrix
      }
      case "+" :: tail => {
        matrix(position._1)(position._2) = "+"
        val nextDirection = direction.next()
        spiral(tail, matrix, position + nextDirection.difference(), nextDirection)
      }
      case head :: tail => {
        matrix(position._1)(position._2) = head
        spiral(tail, matrix, position + direction.difference(), direction)
      }
    }

  }

  def createSprialArray(spiral: List[String], length: Int, direction: Direction): List[String] = {
    if (length == 0) spiral
    else {
      val newSpiral = spiral ::: goDirection(length, direction)
      createSprialArray(newSpiral, length - 1, direction.next())
    }
  }

  def goDirection(length: Int, direction:Direction) = {
    (1 to length).map({
      case i if (i == length) => "+"
      case _ => direction.symbol()
    }).toList
  }

  implicit class TuppleAdd(t: (Int, Int)) {
    def +(p: (Int, Int)) = (p._1 + t._1, p._2 + t._2)
  }
}
