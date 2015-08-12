package linkchecker

import java.util.concurrent.Executors

import org.junit.Test

class LinkCheckerTest {
  @Test
  def getUrl(): Unit = {
    val checker = new LinkChecker
    val result = checker.get("http://www.slipp.net")(Executors.newFixedThreadPool(1))
    println(result.value)
  }
}
