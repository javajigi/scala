package plinscala.circuit2

import scala.collection.mutable.Queue
import scala.collection.mutable.PriorityQueue

class Agenda {
  private var currentTime = 0
  private val timeSegments = new PriorityQueue[Segment]()

  def getCurrentTime = currentTime

  def addItem(time:Int, item:()=>Unit) {
    getTimeSegment(time) match {
      case None =>  
        val queue = new Queue[()=>Unit]()
        queue += item
        timeSegments += Segment(time, queue)
      case Some(segment) => segment.queue += item
    }
  }

  def propagate():Unit = {
    if(!timeSegments.isEmpty) {
      firstItem()
      propagate()
    } 
    else println("Agenda Propagation Done.")
  }

  //gets first agenda item and removes it
  //from the agenda
  private def firstItem = {
    val segment = timeSegments.max
    currentTime = segment.time
    
    val item = segment.queue.dequeue
    if(segment.queue.isEmpty)
      timeSegments.dequeue
    item
  }

  //get the Segment matching time from timeSegments
  private def getTimeSegment(time:Int) = {
    val tmp = timeSegments.filter((s:Segment) => s.time == time)
    if(tmp.isEmpty) None else Some(tmp.head)
  }
}