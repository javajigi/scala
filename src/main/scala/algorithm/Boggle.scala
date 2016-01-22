package algorithm

import scala.collection.immutable.Stack

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

  private var count = 0

  def hasWord(y: Int, x: Int, word: String): Boolean = {
    println(s"y is ${y}, x is ${x}, word : ${word}")
    count += 1
    if (!hasCharFromPosition(grid, y, x, word.charAt(0))) {
      false
    } else if (word.size == 1) {
      println(s"total count : ${count}")
      true
    } else {
        println(s"y is ${y}, x is ${x}, char : ${word.charAt(0)}")
        val positions = getNeighborPositions(y, x);
        // positions.map(p => hasWord(p._1, p._2, word.tail)).contains(true) => 일치하는 문자열을 찾아도 계속 순회함.
        // positions.takeWhile(p => !hasWord(p._1, p._2, word.tail)).size < positions.size => 아래 find와 같은 결과. 단, 구현 코드가 더 복잡함
        positions.find(p => hasWord(p._1, p._2, word.tail)).nonEmpty // find의 경우 조건에 일치하는 element가 있을 경우 loop를 중단
    }
  }
}