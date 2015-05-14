package pearls.essay2

import scala.io.Source
import pearls.support.Utils._

import java.io.File
import java.io.FileWriter
import java.io.PrintWriter
import java.nio.file.Files

object MissFinder {
  private val BASE_DIR = "resources/pearls/essay2/"
  private val tempFileName = BASE_DIR + "tempdata.txt"
  val lowerFileName = BASE_DIR + "lower.txt"
  val higherFileName = BASE_DIR + "higher.txt"

  def getCenter(start: Int, end: Int) = (start + end) / 2

  def getValue(fileName: String): Int = {
  	withFileLines[Int](fileName) {
  		lines => if(lines.isEmpty) 0 else lines.next.toInt
  	}
  }

  def getMissedValue(): Int = getMissedValue(getValue(lowerFileName), getValue(higherFileName))

  def getMissedValue(lowerValue: Int, higherValue: Int): Int = if (higherValue > 0) higherValue - 1 else lowerValue + 1
  
  def findNo(fileName: String): Int = {
    def copyToWorkingFromOriginal(originalFileName: String) {
      if (new File(tempFileName).exists)
        Files.delete(new File(tempFileName).toPath())
      Files.copy(new File(originalFileName).toPath(), new File(tempFileName).toPath())
    }
    
    def divideHighAndLow(center: Int): (Int, Int) = {
      withFileLines[(Int, Int)](tempFileName) {
        lines => {
          withPrintWriter2[(Int, Int)](lowerFileName, higherFileName) {
            (lowerFile, higherFile) => { 
            	def plusOneAndPrint(hl: (Int, Int), line: String) = {
        				if (line.toInt > center) { higherFile.println(line); (hl._1, hl._2 + 1) } 
        				else {lowerFile.println(line); (hl._1 + 1, hl._2)} 
      				}
            	
            	lines.foldLeft(0, 0)(plusOneAndPrint)
            }
          }          
        }
      }
    }
    
    @annotation.tailrec
    def findMissedSector(start: Int, end: Int, originalFileName: String): Int = {
      if (end - start == 1 || end - start == 0) {
        return getMissedValue()
      }

      copyToWorkingFromOriginal(originalFileName)
      
      val center = getCenter(start, end)
      val lh = divideHighAndLow(center)
      println(" lower : " + lh._1 + ", center : " + center + ", higher : " + lh._2)
      if (end - center == lh._2)
        findMissedSector(start, center, lowerFileName)
      else
        findMissedSector(center, end, higherFileName)
    }

    findMissedSector(0, 1000, BASE_DIR + fileName)
  }

  def main(args: Array[String]): Unit = {
    println(findNo("missfiledata.txt"))
  }
}