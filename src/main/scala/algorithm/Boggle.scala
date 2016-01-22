package algorithm

object Boggle {
  def getNeighborPositions(y: Int, x: Int) = {
    var positions: List[Tuple2[Int, Int]] = Nil
    (-1 to 1).foreach(i => (-1 to 1).foreach(j => {
      val newY = y + i
      val newX = x + j
      if (newY > -1 && newY < 5 && newX > -1 && newX < 5) {
        positions = positions :+(newY, newX)
      }
    }))
    positions.filter(t => t !=(y, x))
  }

  def hasCharFromPosition(grid: Array[Array[Char]], y: Int, x: Int, char: Char) = {
    grid.apply(y).apply(x) == char
  }
}

class Boggle(grid: Array[Array[Char]]) {
  import Boggle._
  def hasWord(y: Int, x: Int, word: String): Boolean = {
    if (!hasCharFromPosition(grid, y, x, word.charAt(0))) {
      false
    } else if (word.size == 1) {
      true
    } else {
      println(s"y is ${y}, x is ${x}, char : ${word.charAt(0)}")
      val positions = getNeighborPositions(y, x);
      positions.map(p => hasWord(p._1, p._2, word.tail)).contains(true)
    }
  }
}