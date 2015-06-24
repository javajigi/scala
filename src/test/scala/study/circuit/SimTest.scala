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
}