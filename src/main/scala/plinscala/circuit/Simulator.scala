package plinscala.circuit

import plinscala.circuit.Simulator._

object Simulator {
  type Action = () => Unit
  case class WorkItem(time: Int, action: Action)
}

class Simulator {
  private var agenda: List[WorkItem] = List()
  private var curtime = 0

  def currentTime: Int = curtime

  def addWorkItem(ag: List[WorkItem], item: WorkItem): List[WorkItem] = {
    if (ag.isEmpty || item.time < ag.head.time) item :: ag
    else ag.head :: addWorkItem(ag.tail, item)
  }

  def afterDelay(delay: Int)(block: => Unit) {
    val workItem = new WorkItem(this.currentTime + delay, () => block)
    println(workItem)
    agenda = addWorkItem(agenda, workItem)
  }

  private def next() {
    (agenda) match {
      case item :: rest =>
        agenda = rest
        this.curtime = item.time
        item.action()
      case _ => // empty
    }
  }

  def run() {
    afterDelay(0) {
      println("** simulated started, time = " + currentTime + " **")
    }

    while (!agenda.isEmpty) next()

    println("** simulated finished, time = " + currentTime + " **")
  }

  def inverter(input: Wire, output: Wire) {
    def action() {
      afterDelay(1) {
        output.setSignal(!input.getSignal)
        println("inverter [input : " + input + ", output : " + output + "]")
      }
    }
    input addAction action
  }

  def andGate(input1: Wire, input2: Wire, output: Wire) {
    def action() {
      afterDelay(3) {
        output.setSignal(input1.getSignal & input2.getSignal)
        println("andGate [input1 : " + input1 + ", input2 : " + input2 + ", output : " + output + "]")
      }
    }
    input1 addAction action
    input2 addAction action
  }

  def orGate(input1: Wire, input2: Wire, output: Wire) {
    def action() {
      afterDelay(5) {
        output.setSignal(input1.getSignal | input2.getSignal)
        println("orGate [input1 : " + input1 + ", input2 : " + input2 + ", output : " + output + "]")
      }
    }
    input1 addAction action
    input2 addAction action
  }

  def probe(name: String, wire: Wire) {
    def probeAction() {
      println(name + " " + currentTime +
        " new-value = " + wire.getSignal)
    }
    wire addAction probeAction
  }

  def halfAdder(input1: Wire, input2: Wire, s: Wire, c: Wire) {
    val d, e = new Wire
    orGate(input1, input2, d)
    andGate(input1, input2, c)
    inverter(c, e)
    andGate(d, e, s)
  }

  def fullAdder(input1: Wire, input2: Wire, cIn: Wire, sum: Wire, cOut: Wire) {
    val s, c1, c2 = new Wire
    halfAdder(input1, cIn, s, c1)
    halfAdder(input2, s, sum, c2)
    orGate(c1, c2, cOut)
  }
}