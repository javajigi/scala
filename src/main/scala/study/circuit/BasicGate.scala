package study.circuit

import scala.collection.mutable.MutableList

object BasicGate {
  class Wire() {
    private var signal = false

    def getSignal = this.signal

    def setSignal(s: Boolean) {
      if (s != signal) {
        this.signal = s
      }
    }
  }

  def inverter(input: Wire, output: Wire) {
    output.setSignal(!input.getSignal)
  }

  def andGate(input1: Wire, input2: Wire, output: Wire) {
    output.setSignal(input1.getSignal & input2.getSignal)
  }

  def orGate(input1: Wire, input2: Wire, output: Wire) {
    output.setSignal(input1.getSignal | input2.getSignal)
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
  
  def sum(v1: List[Boolean], v2: List[Boolean]) = {
    val reverseV1 = v1.reverse
    val reverseV2 = v2.reverse
    
    val s = MutableList[Boolean]()
    val input1, input2, cIn, sum, cOut = new Wire
    
    (reverseV1 zip reverseV2).foreach { 
      x => {
        input1.setSignal(x._1)
        input2.setSignal(x._2)
        fullAdder(input1, input2, cIn, sum, cOut)
        s += sum.getSignal
      }
    }
    if (cOut.getSignal) s += cOut.getSignal
    s.reverse
  }
}