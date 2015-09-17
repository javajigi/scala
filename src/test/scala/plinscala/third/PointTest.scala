package plinscala.third

import org.junit.Test
import org.junit.Assert._

class PointTest {
  @Test def isSimilar() {
    val p1 = new Point(2, 3)
    val p2 = new Point(2, 4)
    val p3 = new Point(3, 3)
    
    assertFalse(p1.isNotSimilar(p2))
    assertTrue(p1.isNotSimilar(p3))
    assertTrue(p2.isNotSimilar(p3))
  }
}