package study.spiral


class Spiral {
  def spiralStreamToMatrix(stream: List[String], matrix: Array[Array[String]], length: Int, direction: Direction, start: (Int, Int)): Array[Array[String]] = {
    var d = direction
    var s = start
    stream.foldLeft(matrix)((m, symbol) => symbol match {
      case "+" => {
        println("turn")
        s = s + d.difference()
        matrix(s._1)(s._2) = symbol
        d = d.next()
        matrix
      }
      case _ => {
        println("keep")
        s = s + d.difference()
        matrix(s._1)(s._2) = symbol
        matrix
      }
    })
    matrix
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
