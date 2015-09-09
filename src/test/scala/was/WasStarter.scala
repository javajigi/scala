package was

class WasStarter(serverPort: Int) extends Thread {
  override def run(): Unit = {
    val was = new WebApplicationServer
    was.start(serverPort)
  }
}
