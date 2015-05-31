package study.layout

import study.layout.Element._
import org.scalatest._

class ElementSpec extends FlatSpec with Matchers {
  it should "layout width, height with ArrayElement" in {
    val e = elem(Array("2", "test", "my length"))
    e.height should be (3)
    e.width should be (1)
  }
  
  it should "layout width, height with LineElement" in {
    val e = elem("my length")
    e.height should be (1)
    e.width should be (9)
  }
  
  it should "above" in {
    val first = elem(Array("first"))
    val second = elem(Array("second"))
    val third = elem(Array("third"))
    
    first.above(second).above(third) should be(elem(Array("first ", "second", "third ")))
  }

  it should "beside" in {
	  val first = elem(Array("first", "first2"))
	  val second = elem(Array("second"))
	  val third = elem(Array("third", "third2"))
	  
	  first.beside(second).beside(third) should be (elem(Array("firstsecondthird", "first2      third2")))
  }
}