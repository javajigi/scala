package pearls.support

import java.io.PrintWriter
import java.io.File

object Utils {
  def printToFile(f: File)(op: PrintWriter => Unit) {
    val p = new PrintWriter(f)
    try { op(p) } finally { p.close() }
  }
}