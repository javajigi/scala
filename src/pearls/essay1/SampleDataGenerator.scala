package pearls.essay1

import java.io.PrintWriter
import java.io.File
import scala.util.Random

object SampleDataGenerator {
  def printToFile(f: File)(op: PrintWriter => Unit) {
    val p = new PrintWriter(f)
    try { op(p) } finally { p.close() }
  }

  def main(args: Array[String]): Unit = {
    val shuffles = Random.shuffle(Range(1000000, 9999999).toList)
    printToFile(new File("resources/pearls/essay1/inputdata.txt")) {
      p => shuffles.foreach(p.println)
    }
  }
}