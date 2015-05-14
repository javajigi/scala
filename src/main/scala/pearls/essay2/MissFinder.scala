package pearls.essay2

import scala.io.Source
import pearls.support.Utils._

import java.io.File
import java.io.FileWriter
import java.io.PrintWriter
import java.nio.file.Files

object MissFinder {
  private val prefix = "resources/pearls/essay2/"
  private val tempFileName = prefix + "tempdata.txt"
  val lowerFileName = prefix + "lower.txt"
  val higherFileName = prefix + "higher.txt"

  def getCenter(start: Int, end: Int) = {
    (start + end) / 2
  }

  def getValue(fileName: String): Int = {
    val lines = Source.fromFile(fileName).getLines()
    if (lines.isEmpty)
      return 0
    lines.next().toInt
  }

  def getMissedValue(): Int = {
    getMissedValue(getValue(lowerFileName), getValue(higherFileName))
  }

  def getMissedValue(lowerValue: Int, higherValue: Int): Int = {
    if (higherValue > 0)
      return higherValue - 1
    lowerValue + 1
  }
  
  def findNo(fileName: String): Int = {
    def copyToWorkingFromOriginal(originalFileName: String) {
      if (new File(tempFileName).exists)
        Files.delete(new File(tempFileName).toPath())
      Files.copy(new File(originalFileName).toPath(), new File(tempFileName).toPath())
    }
    
    def divideHighAndLow(center: Int): Tuple2[Int, Int] = {
      var lower, higher = 0
      
      def plusOneAndPrint(lowerFile: PrintWriter, higherFile: PrintWriter, line: String) {
        if (line.toInt > center) { higher += 1; higherFile.println(line) } 
        else {lower += 1; lowerFile.println(line)} 
      }
      
      withFileLines(tempFileName) {
        lines => {
          withPrintWriter2(lowerFileName, higherFileName) {
            (lowerFile, higherFile) => { lines.foreach { line => plusOneAndPrint(lowerFile, higherFile, line) }}
          }          
        }
      }

      println(" lower : " + lower + ", center : " + center + ", higher : " + higher)
      (lower, higher)
    }
    
    def findMissedSector(start: Int, end: Int, originalFileName: String): Int = {
      if (end - start == 1 || end - start == 0) {
        return getMissedValue()
      }

      copyToWorkingFromOriginal(originalFileName)
      
      val center = getCenter(start, end)
      val lowAndHigh = divideHighAndLow(center)
      
      if (end - center == lowAndHigh._2)
        findMissedSector(start, center, lowerFileName)
      else
        findMissedSector(center, end, higherFileName)
    }

    findMissedSector(0, 1000, prefix + fileName)
  }

  def main(args: Array[String]): Unit = {
    println(findNo("missfiledata.txt"))
  }
}