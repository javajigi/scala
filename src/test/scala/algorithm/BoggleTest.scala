package algorithm

import org.junit.Assert._
import org.junit.Test

class BoggleTest {
  val grid = Array(
    Array('N', 'N', 'N', 'N', 'S'),
    Array('N', 'E', 'E', 'E', 'N'),
    Array('N', 'E', 'Y', 'E', 'N'),
    Array('N', 'E', 'E', 'E', 'N'),
    Array('N', 'N', 'N', 'N', 'N'))

  val grid2 = Array(
    Array('U', 'R', 'L', 'P', 'M'),
    Array('X', 'P', 'R', 'E', 'T'),
    Array('G', 'I', 'A', 'E', 'T'),
    Array('X', 'T', 'N', 'Z', 'Y'),
    Array('X', 'O', 'Q', 'R', 'S'))

  @Test def hasWord(): Unit = {
    val boggle = new Boggle(grid)
    assertTrue(boggle.hasWord(2, 2, "YES"))
    assertFalse(boggle.hasWord(2, 2, "NO"))
    assertTrue(boggle.hasWord(2, 2, "YESN"))
  }

  @Test def hasWordWithGrid2(): Unit = {
    val boggle = new Boggle(grid2)
    assertTrue(boggle.hasWord(1, 1, "PRETTY"))
    assertTrue(boggle.hasWord(2, 0, "GIRL"))
    assertTrue(boggle.hasWord(1, 2, "REPEAT"))
    assertFalse(boggle.hasWord(1, 1, "PRETTYM"))
  }

  @Test def hasCharFromPosition(): Unit = {
    assertTrue(Boggle.hasCharFromPosition(grid, 2, 2, 'Y'))
    assertTrue(Boggle.hasCharFromPosition(grid, 0, 4, 'S'))
  }

  @Test def getNeighborPositions(): Unit = {
    println(Boggle.getNeighborPositions(2, 2))
    println(Boggle.getNeighborPositions(0, 0))
    println(Boggle.getNeighborPositions(4, 4))
  }
}
