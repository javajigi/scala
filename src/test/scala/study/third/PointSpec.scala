package study.third

import org.scalatest._

class PointSpec extends FlatSpec with Matchers {
  it should "x가 같으면 같은 Point" in {
    val p1 = new Point(2, 3)
    val p2 = new Point(2, 4)
    val p3 = new Point(3, 3)
    
    p1.isSimilar(p2) should be (true)
    p2.isSimilar(p3) should be (false)
    p1.isSimilar(p3) should be (false)
  }
}