package study.circuit

import study.circuit.BasicGate._

import org.scalatest._

class BasicGateTest extends FlatSpec with Matchers {
  it should "inverter" in {
     val input = new Wire
     input.setSignal(true)
     
     val output = new Wire
     inverter(input, output)
     output.getSignal should be (false)
     
     input.setSignal(false)
     inverter(input, output)
     output.getSignal should be (true)
  }
  
  it should "andGate" in {
    val input1, input2, output = new Wire
    andGate(input1, input2, output)
    output.getSignal should be (false)
    
    input1.setSignal(true)
    andGate(input1, input2, output)
    output.getSignal should be (false)
    
    input2.setSignal(true)
    andGate(input1, input2, output)
    output.getSignal should be (true)
  }
  
  it should "halfAdder" in {
    val input1, input2, s, c = new Wire
    halfAdder(input1, input2, s, c)
    s.getSignal should be (false)
    c.getSignal should be (false)
    
    input1.setSignal(true)
    halfAdder(input1, input2, s, c)
    s.getSignal should be (true)
    c.getSignal should be (false)
    
    input2.setSignal(true)
    halfAdder(input1, input2, s, c)
    s.getSignal should be (false)
    c.getSignal should be (true)
  }
  
  it should "fullAdder" in {
    val input1, input2, cIn, sum, cOut = new Wire
    fullAdder(input1, input2, cIn, sum, cOut)
    sum.getSignal should be (false)
    cOut.getSignal should be (false)
    
    input1.setSignal(true)
    input2.setSignal(true)
    cIn.setSignal(true)
    fullAdder(input1, input2, cIn, sum, cOut)
    sum.getSignal should be (true)
    cOut.getSignal should be (true)
  }
  
  it should "sum" in {
    val input1 = List(true, false)
    val input2 = List(true, false)
    val s = sum(input1, input2)
    s should be (List(true, false, false))
  }
}