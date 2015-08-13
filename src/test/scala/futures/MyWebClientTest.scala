package futures

import linkchecker.AsyncWebClient
import org.junit.Test

class MyWebClientTest {
  @Test
  def getSync(): Unit = {
    val start = System.currentTimeMillis()
    val html = MyWebClient.getSync("http://www.ticketmonster.co.kr/home");
    val end = System.currentTimeMillis()
    println("execution time : " + (end - start))

    MyWebClient.shutdown
  }

  @Test
  def getAsync(): Unit = {
    import scala.concurrent.ExecutionContext.Implicits.global
    val start = System.currentTimeMillis()
    val f = MyWebClient.getAsync("http://www.ticketmonster.co.kr/home")
    val end = System.currentTimeMillis()
    println("execution time : " + (end - start))

    println(f map println)

    MyWebClient.shutdown
  }
}
