package study.circuit

class Simulator {
  type Action = () => Unit
  
  private var agenda: List[Action] = List()
  
  def addAction(a: Action) {
    agenda = agenda :+ a
  }
  
  private def next() {
    (agenda) match {
      case a :: rest =>
        agenda = rest
        a()
      case _ => // empty
    }
  }
  
  def run() {
    while(!agenda.isEmpty) next()
  }
  
  def inverter(input: Wire, output: Wire) {
    def action() {
    	println("inverter [input : " + input + ", output : " + output + "]")
    	output.setSignal(!input.getSignal)
    }
    addAction(action)
  }

  def andGate(input1: Wire, input2: Wire, output: Wire) {
    def action() {
    	println("andGate [input1 : " + input1 + ", input2 : " + input2 + ", output : " + output + "]")
    	output.setSignal(input1.getSignal & input2.getSignal)
    }
    addAction(action)
  }

  def orGate(input1: Wire, input2: Wire, output: Wire) {
    def action() {
    	println("orGate [input1 : " + input1 + ", input2 : " + input2 + ", output : " + output + "]")
    	output.setSignal(input1.getSignal | input2.getSignal)
    }
    addAction(action)
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