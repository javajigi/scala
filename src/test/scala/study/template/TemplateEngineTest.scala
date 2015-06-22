package study.template

import org.junit.Test
import org.junit.Assert._

class TemplateEngineTest {
  @Test def process() {
    val params = Map("name" -> "재성")
    val actual = TemplateEngine.process("my name is ${name}", params)
    assertEquals("my name is 재성", actual)
  }
  
  @Test def replace() {
    val pattern = "\\$\\{(.+?)\\}".r
    var str = "${lang} is ${message}"
    
    val params = Map("lang" -> "Scala", "message" -> "Scalable")
    
    val iterator = pattern findAllMatchIn str
    iterator.foreach(m => {
       str = str.replace(m.group(0), params(m.group(1)))
    })
    println(str)
  }
}