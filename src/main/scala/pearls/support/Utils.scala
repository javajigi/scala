package pearls.support

import java.io.File
import java.io.FileWriter
import java.io.PrintWriter
import scala.io.Source

object Utils {
  def withPrintWriter(fileName: String)(op: PrintWriter => Unit) {
    withPrintWriter(new File(fileName))(op)
  }
  
  def withPrintWriter(f: File)(op: PrintWriter => Unit) {
    val p = new PrintWriter(f)
    try { op(p) } finally { p.close() }
  }

  def withPrintWriter2(fileName1: String, fileName2: String)(op: (PrintWriter, PrintWriter) => Unit) {
    withPrintWriter2(new File(fileName1), new File(fileName2))(op)
  }  
  
  def withPrintWriter2(f1: File, f2: File)(op: (PrintWriter, PrintWriter) => Unit) {
    val p1 = new PrintWriter(f1)
    val p2 = new PrintWriter(f2)
    try { op(p1, p2) } finally { p1.close; p2.close }
  }
  
  def withFileWriter(f: File)(op: FileWriter => Unit) {
    val fw = new FileWriter(f, true)
    try { op(fw) } finally { fw.close() }
  }
  
  def withFileLines(fileName: String)(op: Iterator[String] => Unit) {
    val source = Source.fromFile(fileName)
    try { op(source.getLines()) } finally { source.close }
  }
}