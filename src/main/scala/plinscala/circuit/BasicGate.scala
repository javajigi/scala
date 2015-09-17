package plinscala.circuit

import scala.collection.mutable.MutableList

object BasicGate {
  def inverter(input: Wire, output: Wire) {
    println("inverter [input : " + input + ", output : " + output + "]")
    output.setSignal(!input.getSignal)
  }

  def andGate(input1: Wire, input2: Wire, output: Wire) {
    println("andGate [input1 : " + input1 + ", input2 : " + input2 + ", output : " + output + "]")
    output.setSignal(input1.getSignal & input2.getSignal)
  }

  def orGate(input1: Wire, input2: Wire, output: Wire) {
    println("orGate [input1 : " + input1 + ", input2 : " + input2 + ", output : " + output + "]")
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