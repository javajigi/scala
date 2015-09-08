package was

import java.net.{Socket, ServerSocket}

import org.slf4j.LoggerFactory

object WebApplicationServer {
  val log = LoggerFactory.getLogger(WebApplicationServer.getClass)
  val DEFAULT_PORT = 7000;

  def main(args: Array[String]) {
    val listenSocket = new ServerSocket(DEFAULT_PORT)
    log.info("Web Application Server started. Start Port is {}", DEFAULT_PORT)

    var connection: Socket = null
    while( (connection = listenSocket.accept()) != null ) {
      val requestHandler = new RequestHandler(connection)
      requestHandler.start
    }
  }
}
