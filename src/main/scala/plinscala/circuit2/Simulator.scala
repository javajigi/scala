package plinscala.circuit2

class Simulator {
  val theAgenda = new Agenda()
  val inverterDelay = 2
  val andGateDelay = 3
  val orGateDelay = 5

  //inverter
  def inverter(in: Wire, out: Wire) = {
    in.addAction(
      () => {
        val newVal = !in.signal
        afterDelay(inverterDelay, () => (out.setSignal(newVal)), theAgenda)
      })
    "Ok"
  }

  //and gate
  def andGate(in1: Wire, in2: Wire, out: Wire) = {
    val action = () => {
      val newVal = in1.signal && in2.signal
      afterDelay(andGateDelay, () => (out.setSignal(newVal)), theAgenda)
    }
    in1.addAction(action)
    in2.addAction(action)
    "Ok"
  }

  //or gate
  def orGate(in1: Wire, in2: Wire, out: Wire) = {
    val action = () => {
      val newVal = in1.signal || in2.signal
      afterDelay(orGateDelay,
        () => (out.setSignal(newVal)),
        theAgenda)
    }
    in1.addAction(action)
    in2.addAction(action)
    "Ok"
  }

  //half-adder
  def halfAdder(in1: Wire, in2: Wire, sum: Wire, carry: Wire) = {
    val t1 = new Wire()
    val t2 = new Wire()
    orGate(in1, in2, t1)
    andGate(in1, in2, carry)
    inverter(carry, t2)
    andGate(t1, t2, sum)
    "Ok"
  }
  
  def fullAdder(input1: Wire, input2: Wire, cIn: Wire, sum: Wire, cOut: Wire) = {
    val s, c1, c2 = new Wire
    halfAdder(input1, cIn, s, c1)
    halfAdder(input2, s, sum, c2)
    orGate(c1, c2, cOut)
    "Ok"
  }  

  //Probe
  def probe(name: String, wire: Wire, agenda: Agenda) {
    wire.addAction(
      () =>
        println(name + " " + agenda.getCurrentTime
          + " ,New Value = " + wire.signal))
  }

  //after-delay
  def afterDelay(delay: Int, proc: () => Unit, agenda: Agenda) {
    require(delay >= 0)
    agenda.addItem(agenda.getCurrentTime + delay, proc)
  }
  
  def getAgenda() = theAgenda
}