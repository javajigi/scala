package pearls.essay1

import java.io.File
import scala.io.Source
import pearls.support.Utils
import scala.util.Sorting

object Sort {
  def changeBit(bitMap: Array[Boolean], position: Int) = {
    if (position < 0) throw new IllegalArgumentException
    if (bitMap(position)) throw new IllegalArgumentException
    bitMap(position) = true
  }
  
  def sortUsingBitmap(file: String): Unit = {
    val difference = 1000000
    val bitMap = new Array[Boolean](9000000)
    val lines = Source.fromFile(file).getLines()
    lines.foreach {
      x => changeBit(bitMap, x.toInt - difference)
    }
    
    Utils.withPrintWriter(new File("resources/pearls/essay1/outputdata.txt")) {
      p => for(i <- bitMap.indices) if(bitMap(i)) p.println(i + difference) 
    }
  }
  
  def mutableQuickSort(file: String): Unit = {
    val lines = Source.fromFile(file).getLines()
    val values = lines.map[Int]( x => x.toInt ).toArray
    Sorting.quickSort(values)
    
    Utils.withPrintWriter(new File("resources/pearls/essay1/outputdata2.txt")) {
      p => values.foreach(p.println)
    }
  }
  
  def main(args: Array[String]): Unit = {
    val runtime = Runtime.getRuntime
    runtime.gc()
    var memoryBefore = runtime.totalMemory() - runtime.freeMemory() 
    var start = System.currentTimeMillis()
    sortUsingBitmap("resources/pearls/essay1/inputdata.txt")
    var end = System.currentTimeMillis();
    println("소요 시간 : " + (end - start) + " millsec")
    var memoryAfter = runtime.totalMemory() - runtime.freeMemory()
    println("Bitmap Usage Memory : " + (memoryAfter - memoryBefore))
    
    runtime.gc()
    memoryBefore = runtime.totalMemory() - runtime.freeMemory()
    start = System.currentTimeMillis()
    mutableQuickSort("resources/pearls/essay1/inputdata.txt")
    end = System.currentTimeMillis();
    println("소요 시간 : " + (end - start) + " millsec")
    memoryAfter = runtime.totalMemory() - runtime.freeMemory()
    println("QuickSort Usage Memory : " + (memoryAfter - memoryBefore))
  }
}