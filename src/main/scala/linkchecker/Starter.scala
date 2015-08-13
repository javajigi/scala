package linkchecker

import akka.actor.{Actor, Props, ActorSystem}


class Starter extends Actor {
  import Receptionist._
  import Starter._

  def receive = {
    case Start =>
      val receptionist = context.actorOf(Props[Receptionist], name="receptionist")
      receptionist ! Get("http://www.slipp.net")
    case Result(url, links) =>
      println(s"last link result : ${links.size}")
      context.stop(self)
      context.stop(sender)
  }
}

object Starter {
  case object Start
  
  def main(args: Array[String]) {
    val system = ActorSystem("LinkCheckerSystem")
    val appKernel = system.actorOf(Props[Starter], name="appKernel")
    appKernel ! Start
  }
}
