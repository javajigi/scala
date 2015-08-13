package linkchecker

import akka.actor.{ActorLogging, Status, Actor}

import scala.util.{Failure, Success}

object Getter {
  case object GetUrl
  case object Done
  case object Abort
}

class Getter(url: String, depth: Int) extends Actor with ActorLogging {
  import Getter._

//  implicit val exec = context.dispatcher
//
//  val future = WebClient.get(url)
//
//  future onComplete {
//    case Success(body) => self ! body
//    case Failure(err) => self ! Status.Failure(err)
//  }

  def receive = {
    case GetUrl =>
      log.debug("GET URL : {}", url)
      for (link <- WebClient.findLinks(url))
        context.parent ! Controller.Check(link, depth)
      stop()
    case _: Status.Failure => stop()
  }

  def stop(): Unit = {
    context.parent ! Done
    context.stop(self)
  }


}
