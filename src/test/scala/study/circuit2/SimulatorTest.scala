package study.circuit2

import org.scalatest._

class SimulatorTest extends FlatSpec with Matchers {
  it should "half adder" in {
    val sim = new Simulator
    val agenda = sim.getAgenda()
    val input1, input2, s, c = new Wire
    sim.probe("sum", s, agenda)
    sim.probe("carry", c, agenda)

    sim.halfAdder(input1, input2, s, c)
    agenda.propagate()
    s.getSignal should be(false)
    c.getSignal should be(false)

    input1.setSignal(true)
    agenda.propagate()
    s.getSignal should be(true)
    c.getSignal should be(false)
  }

  it should "full adder" in {
    val sim = new Simulator
    val agenda = sim.getAgenda()
    val input1, input2, cIn, sum, cOut = new Wire
    sim.probe("sum", sum, agenda)
    sim.probe("carry", cOut, agenda)

    sim.fullAdder(input1, input2, cIn, sum, cOut)
    agenda.propagate()
    sum.getSignal should be(false)
    cOut.getSignal should be(false)

    input1.setSignal(true)
    input2.setSignal(true)
    cIn.setSignal(true)
    agenda.propagate()
    sum.getSignal should be(true)
    cOut.getSignal should be(true)
  }
}