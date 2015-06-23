package study.circuit

import org.scalatest._

class SimulatorTest extends FlatSpec with Matchers {
  it should "add action" in {
    def action1() {
      println("inverter")
    }
    def action2() {
      println("andGate")
    }
    val sim = new Simulator
    sim.addAction(action1)
    sim.addAction(action2)
    sim.run()    
  }
  
  it should "half adder" in {
    val sim = new Simulator
    val input1, input2, s, c = new Wire
    sim.halfAdder(input1, input2, s, c)
    sim.run()
    s.getSignal should be (false)
    c.getSignal should be (false)
    
    input1.setSignal(true)
    sim.halfAdder(input1, input2, s, c)
    sim.run()
    s.getSignal should be (true)
    c.getSignal should be (false)    
    
//    input2.setSignal(true)
//    halfAdder(input1, input2, s, c)
//    s.getSignal should be (false)
//    c.getSignal should be (true) 
  }
}