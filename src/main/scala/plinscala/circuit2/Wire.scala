package plinscala.circuit2

class Wire() {
  var signal = false
  private var actions:List[()=>Unit] = List()

  def getSignal = this.signal
  
  def setSignal(b:Boolean) {
    if(b != signal) {
      signal = b
      //execute all actions
      actions.foreach((proc:()=>Unit)  => proc())
    }
  }
  
  def addAction(proc:()=>Unit) { 
    actions = proc :: actions 
    proc()
  }
  
  override def toString() = "[signal=" + this.signal + "]"
}