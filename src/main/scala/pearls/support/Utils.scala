package pearls.support

import java.io.PrintWriter
import java.io.File
import java.io.FileWriter

object Utils {
  def withPrintWriter(f: File)(op: PrintWriter => Unit) {
    val p = new PrintWriter(f)
    try { op(p) } finally { p.close() }
  }
  
  def withFileWriter(f: File)(op: FileWriter => Unit) {
    val fw = new FileWriter(f, true)
    try { op(fw) } finally { fw.close() }
  }
}