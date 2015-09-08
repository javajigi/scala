package fp

import org.junit.Test

class RacingTest {
  @Test
  def run(): Unit = {
    val racing = new Racing()
    racing.run(List(1, 1, 1), 5)
  }
}
