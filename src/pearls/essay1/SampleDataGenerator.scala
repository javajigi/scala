package pearls.essay1

import java.io.PrintWriter
import java.io.File
import scala.util.Random
import pearls.support.Utils

object SampleDataGenerator {
  def main(args: Array[String]): Unit = {
    val shuffles = Random.shuffle(Range(1000000, 9999999).toList)
    Utils.printToFile(new File("resources/pearls/essay1/inputdata.txt")) {
      p => shuffles.foreach(p.println)
    }
  }
}