package plinscala.circuit2

import scala.collection.mutable.Queue

case class Segment(val time: Int,
                   val queue: Queue[() => Unit]) extends Ordered[Segment] {
  require(time >= 0)

  override def compare(that: Segment) = {
    if (that.time == time) 0
    else if (that.time < time) -1
    else 1
  }
}