package was

import java.net.{Socket, ServerSocket}

import org.slf4j.LoggerFactory

object WebApplicationServer {
  val log = LoggerFactory.getLogger(WebApplicationServer.getClass)
  val DEFAULT_PORT = 7000;

  def main(args: Array[String]): Unit = {
    val was = new WebApplicationServer
    was.start(DEFAULT_PORT)
  }
}

class WebApplicationServer {
  val log = LoggerFactory.getLogger(classOf[WebApplicationServer])

  var listenSocket: ServerSocket = _

  def start(port: Int): Unit = {
    listenSocket = new ServerSocket(port)
    log.info("Web Application Server started. Start Port is {}", port)

    var connection = listenSocket.accept
    while( connection != null) {
      val requestHandler = new RequestHandler(connection)
      requestHandler.start
      connection = listenSocket.accept
    }
  }

  def stop(): Unit = {
    listenSocket.close()
  }

}