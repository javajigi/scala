package study.circuit

import org.scalatest._
import study.circuit.Simulator._

class SimulatorTest extends FlatSpec with Matchers {
//  it should "half adder" in {
//    val sim = new Simulator
//    val input1, input2, s, c = new Wire
//    sim.probe("sum", s)
//    sim.probe("carry", c)    
//    
//    sim.halfAdder(input1, input2, s, c)
//    sim.run
//    s.getSignal should be (false)
//    c.getSignal should be (false)
//    
//    input1.setSignal(true)
//    sim.run()
//    s.getSignal should be (true)
//    c.getSignal should be (false)    
//  }
  
  it should "full adder" in {
    val sim = new Simulator
    val input1, input2, cIn, sum, cOut = new Wire
    sim.probe("sum", sum)
    sim.probe("carry", cOut)     
    
    sim.fullAdder(input1, input2, cIn, sum, cOut)
    sim.run()
    sum.getSignal should be (false)
    cOut.getSignal should be (false)
    
    input1.setSignal(true)
    input2.setSignal(true)
    cIn.setSignal(true)
    sim.run()
    sum.getSignal should be (true)
    cOut.getSignal should be (true)
  }
}