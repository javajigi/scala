package pearls.essay2

import scala.io.Source
import pearls.support.Utils
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter
import java.nio.file.Files

object MissFinder {
  private val prefix = "resources/pearls/essay2/"
  private val lowerFileName = prefix + "lower.txt"
  private val higherFileName = prefix + "higher.txt"
  private val tempFileName = prefix + "tempdata.txt"
    
  def findNo(fileName: String): Int = {
    def findMissedSector(start: Int, end: Int, missedFileName: String): Int = {
      println("start : " + start + " end : " + end)
      
      if (Source.fromFile(missedFileName).getLines.size == 0)
        return 289
        
      val center = start + ((end - start - 1) / 2)
      var lower = 0
      var higher = 0
      
      Files.copy(new File(missedFileName).toPath(), new File(tempFileName).toPath())
      
      val lowerPW = new PrintWriter(new File(lowerFileName))
      val higherPW = new PrintWriter(new File(higherFileName))
      val linesSource = Source.fromFile(tempFileName)
      val lines = linesSource.getLines
      lines.foreach(line => {
        if (line.toInt > center) {
          higher += 1
          higherPW.println(line)
        } else {
          lower += 1
          lowerPW.println(line)
        }
      })

      lowerPW.close
      higherPW.close
      linesSource.close
      Files.delete(new File(tempFileName).toPath())

      println("center : " + center + " higher : " + higher + " lower : " + lower)
      if (end - center > higher)
        findMissedSector(center, end, higherFileName)
      else
        findMissedSector(0, lower, lowerFileName)
    }
    
    findMissedSector(0, 1000, prefix + fileName)
  }

  def main(args: Array[String]): Unit = {
    println(findNo("missfiledata.txt"))
  }
}