package study.circuit

import org.scalatest._
import study.circuit.Simulator._

class SimulatorTest extends FlatSpec with Matchers {
  it should "half adder" in {
    val sim = new Simulator
    val input1, input2, s, c = new Wire
    sim.probe("sum", s)
    sim.probe("carry", c)    
    
    sim.halfAdder(input1, input2, s, c)
    sim.run()
    s.getSignal should be (false)
    c.getSignal should be (false)
    
    input1.setSignal(true)
    sim.run()
    s.getSignal should be (true)
    c.getSignal should be (false)    
    
    input2.setSignal(true)
    sim.run()
    s.getSignal should be (false)
    c.getSignal should be (true) 
  }
}