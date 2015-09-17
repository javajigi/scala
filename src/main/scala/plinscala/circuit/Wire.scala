package plinscala.circuit

import plinscala.circuit.Simulator._

class Wire {
  private var signal = false
  
  private var actions: List[Action] = List()

  def getSignal = this.signal

  def setSignal(s: Boolean) {
    if (s != signal) {
      this.signal = s
      actions foreach (_())
    }
  }
  
  def addAction(a: Action) {
    actions = a :: actions
    a()
  }

  override def toString() = "[signal=" + this.signal + "]"
}