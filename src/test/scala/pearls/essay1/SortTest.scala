package pearls.essay1

import org.junit.Assert._
import org.junit.Test

class SortTest {
  @Test def sort {
    Sort.sortUsingBitmap("resources/pearls/essay1/inputdata.txt")
  }

  @Test def changeBit() {
    val position = 0;
    val bitMap = new Array[Boolean](5)
    Sort.changeBit(bitMap, position)
    assertTrue(bitMap(position))
  }

  @Test(expected = classOf[IllegalArgumentException])
  def changeBit_이미_존재하는_값() {
    val position = 0;
    val bitMap = new Array[Boolean](5)
    Sort.changeBit(bitMap, position)
    Sort.changeBit(bitMap, position)
  }

  @Test(expected = classOf[IllegalArgumentException])
  def changeBit_0보다_작은_값() {
    val bitMap = new Array[Boolean](5)
    Sort.changeBit(bitMap, -1)
  }
}