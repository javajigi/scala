package pearls.essay2

import org.junit.Test
import org.junit.Assert._

class MissFinderTest {
  @Test def getCenter() {
    assertEquals(5, MissFinder.getCenter(0, 10))
    assertEquals(2, MissFinder.getCenter(0, 5))
    assertEquals(4, MissFinder.getCenter(3, 5))
  }
}