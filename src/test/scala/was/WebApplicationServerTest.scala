package was

import org.junit.{Before, Test}
import org.slf4j.LoggerFactory
import org.springframework.web.client.RestTemplate

class WebApplicationServerTest {
  private val log = LoggerFactory.getLogger(classOf[WebApplicationServerTest])

  val serverPort = 3333;
  val baseUrl = "http://localhost:" + serverPort

  @Test
  def helloworld(): Unit = {
    val thread = new Thread() {
      override def run(): Unit = {
        val was = new WebApplicationServer
        was.start(serverPort)
      }
    }
    thread.start()

    val restTemplate = new RestTemplate
    val result = restTemplate.getForEntity(baseUrl, classOf[String])
    log.debug("result : {}", result.getStatusCode)
  }
}
