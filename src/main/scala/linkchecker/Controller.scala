package linkchecker

import akka.actor.{Props, ActorRef, ActorLogging, Actor}
import linkchecker.Getter.GetUrl

import scala.concurrent.duration._

object Controller {
  case class Check(url: String, depth: Int)
  case class Result(cache: Set[String])
  case object ReceiveTimeout
  case object Timeout
}

class Controller extends Actor with ActorLogging {
  import Controller._

  var cache = Set.empty[String]
  var children = Set.empty[ActorRef]

  def receive = {
    case Check(url, depth) =>
      log.debug("{} checking {}", depth, url)
      if (!cache(url) && depth > 0) {
          val getter = context.actorOf(Props(new Getter(url, depth - 1)))
          children += getter
          getter ! GetUrl
      }
      cache += url
    case Getter.Done =>
      children -= sender
      if (children.isEmpty) context.parent ! Result(cache)
  }
}
