package study.circuit

import org.scalatest._

class SimTest extends FlatSpec with Matchers {
  it should "inverter" in {
    val input, output = new Wire
    val sim = new Simulator
    sim.inverter(input, output)
    sim.run()
    output.getSignal should be (true)
  }
  
  it should "andGate" in {
    val input1, input2, output = new Wire
    val sim = new Simulator
    sim.andGate(input1, input2, output)
    sim.run()
    output.getSignal should be (false)
    
    input1.setSignal(true)
    sim.run()
    output.getSignal should be (false)
    
//    input2.setSignal(true)
//    andGate(input1, input2, output)
//    output.getSignal should be (true)
  }
}