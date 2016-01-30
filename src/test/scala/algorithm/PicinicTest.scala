package algorithm

import org.junit.Assert._
import org.junit.Test

class PicnicTest {
  @Test def getCombinationCountFourFriend() {
    val totalFriendPair = List((0, 1), (1, 2), (2, 3), (0, 3), (0, 2), (1, 3))
    val picnic = new Picnic(4, totalFriendPair)
    assertEquals(3, picnic.getCombinationCount())
  }

  @Test def getCombinationCountSixFriend() {
    val totalFriendPair = List((0, 1), (0, 2), (1, 2), (1, 3), (1, 4), (2, 3), (2, 4), (3, 4), (3, 5), (4, 5))
    val picnic = new Picnic(6, totalFriendPair)
    assertEquals(4, picnic.getCombinationCount())
  }
}
