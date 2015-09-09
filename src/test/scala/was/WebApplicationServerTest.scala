package was

import org.junit.{Before, Test}
import org.slf4j.LoggerFactory
import org.springframework.web.client.RestTemplate

class WebApplicationServerTest {
  private val log = LoggerFactory.getLogger(classOf[WebApplicationServerTest])

  val serverPort = 3333;
  val baseUrl = "http://localhost:" + serverPort

  @Before
  def setup(): Unit = {
    val starter = new WasStarter(serverPort)
    starter.start()
  }

  @Test
  def helloworld(): Unit = {
    val restTemplate = new RestTemplate
    val result = restTemplate.getForEntity(baseUrl, classOf[String])
    log.debug(s"result : ${result.getStatusCode}, body : ${result.getBody}")
  }
}
