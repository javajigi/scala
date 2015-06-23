package study.circuit

class Wire {
  private var signal = false

  def getSignal = this.signal

  def setSignal(s: Boolean) {
    if (s != signal) {
      this.signal = s
    }
  }

  override def toString() = "[signal=" + this.signal + "]"
}