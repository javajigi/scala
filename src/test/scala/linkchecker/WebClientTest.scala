package linkchecker

import java.util.concurrent.Executors

import org.junit.Test

import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits._

class WebClientTest {
  @Test
  def getUrl(): Unit = {
    val result = WebClient.get("http://www.slipp.net")(Executors.newFixedThreadPool(1))
    println(result.value)
  }

  @Test
  def findLinks(): Unit = {
    val links = WebClient.findLinks("http://www.slipp.net")
    links.foreach(link => println(link))
  }
}
