package was

import java.io.DataOutputStream
import java.net.Socket

import org.slf4j.LoggerFactory

class RequestHandler(c: Socket) extends Thread {
  val log = LoggerFactory.getLogger(classOf[RequestHandler])

  val connection = c

  override def run(): Unit = {
    log.debug("new client connnect. Connected IP : {}, Port : {}", connection.getInetAddress, connection.getPort)

    val in = connection.getInputStream
    val dos = new DataOutputStream(connection.getOutputStream)
    response200Header(dos)
    responseBody(dos, "Hello World!".getBytes)
  }

  private def response200Header(dos: DataOutputStream): Unit = {
    dos.writeBytes("HTTP/1.1 200 OK \r\n");
    dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
    dos.writeBytes("\r\n");
  }

  private def responseBody(dos: DataOutputStream, body: Array[Byte]): Unit = {
    dos.write(body, 0, body.length);
    dos.writeBytes("\r\n");
    dos.flush();
  }
}
