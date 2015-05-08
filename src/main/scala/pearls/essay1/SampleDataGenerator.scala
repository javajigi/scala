package pearls.essay1

import java.io.PrintWriter
import java.io.File
import scala.util.Random
import pearls.support.Utils

object SampleDataGenerator {
	val prefix = "resources/pearls/essay1/"
	
	def generate(file: String) {
		val shuffles = Random.shuffle(Range(1000000, 9999999).toList)
    val splitedShuffles = shuffles.take(1000000)
    printToFile(prefix + file, splitedShuffles)
	}
	
	private def printToFile(file: String, numbers: List[Int]) {
		Utils.printToFile(new File(file)) {
      p => numbers.foreach(p.println)
    }
	}
	
	def generateSplitedFile() {
		val shuffles = Random.shuffle(Range(1000000, 9999999).toList)
    printToFile(prefix + "splitedData1.txt", shuffles.take(1000000))
    printToFile(prefix + "splitedData2.txt", shuffles.drop(1000000).take(1000000))
	}
	
  def main(args: Array[String]): Unit = {
  	// generate("inputdata.txt")
  	generateSplitedFile()
  }
}