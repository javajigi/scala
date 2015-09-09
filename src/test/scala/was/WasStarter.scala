package was

import java.net.{SocketException, Socket}

class WasStarter(serverPort: Int) extends Thread {
  override def run(): Unit = {
    if (!isPortInUse(serverPort)) {
      val was = new WebApplicationServer
      was.start(serverPort)
    }
  }

  private def isPortInUse(port: Int): Boolean = {
    var result = false
    try {
      (new Socket("127.0.0.1", port))
      result = true
    } catch {
      case e: SocketException => result = false
    }
    result
  }
}
