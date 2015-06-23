package study.circuit

import study.circuit.Simulator._

object Simulator {
  type Action = () => Unit
  case class WorkItem(time: Int, action: Action)
}

class Simulator {
  private var agenda: List[WorkItem] = List()
  
  def addWorkItem(item: WorkItem) {
    agenda = agenda :+ item
  }
  
  def afterDelay(delay: Int)(block: () => Unit) {
    // after delay
    block()
  }  
  
  private def next2() {
    (agenda) match {
      case a :: rest =>
        agenda = rest
        afterDelay(a.time)(a.action)
      case _ => // empty
    }
  }  
  
  def run() {
    while(!agenda.isEmpty) next2()
  }  
  
  def inverter(input: Wire, output: Wire) {
    def action() {
    	println("inverter [input : " + input + ", output : " + output + "]")
    	output.setSignal(!input.getSignal)
    }
    addWorkItem(new WorkItem(2, action))
  }

  def andGate(input1: Wire, input2: Wire, output: Wire) {
    def action() {
    	println("andGate [input1 : " + input1 + ", input2 : " + input2 + ", output : " + output + "]")
    	output.setSignal(input1.getSignal & input2.getSignal)
    }
    addWorkItem(new WorkItem(3, action))
  }

  def orGate(input1: Wire, input2: Wire, output: Wire) {
    def action() {
    	println("orGate [input1 : " + input1 + ", input2 : " + input2 + ", output : " + output + "]")
    	output.setSignal(input1.getSignal | input2.getSignal)
    }
    addWorkItem(new WorkItem(3, action))
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