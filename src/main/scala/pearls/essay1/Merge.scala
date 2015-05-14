package pearls.essay1

import scala.io.Source
import java.io.File
import pearls.support.Utils
import scala.collection.mutable.ListBuffer

object Merge {
  val prefix = "resources/pearls/essay1/"

  def merge(leftFile: String, rightFile: String) {
    @annotation.tailrec
    def merge(merged: ListBuffer[Int], leftValues: List[String], rightValues: List[String]): List[Int] = {
      if (leftValues == Nil) (merged ++= rightValues.map(x => x.toInt)).toList
      else if (rightValues == Nil) (merged ++= leftValues.map(x => x.toInt)).toList
      else {
        val leftHead = leftValues.head.toInt
        val rightHead = rightValues.head.toInt
        if (leftHead < rightHead) {
          merge(merged += leftHead, leftValues.tail, rightValues)
        } else {
          merge(merged += rightHead, leftValues, rightValues.tail)
        }
      }
    }

    val leftLines = Source.fromFile(leftFile).getLines().toList
    val rightLines = Source.fromFile(rightFile).getLines().toList
    val mergedLines = merge(ListBuffer(), leftLines.sorted, rightLines.sorted)
    Utils.withPrintWriter(new File("resources/pearls/essay1/outputdata2.txt")) {
      p => mergedLines.foreach(p.println)
    }
  }

  def main(args: Array[String]): Unit = {
    merge(prefix + "splitedData1.txt", prefix + "splitedData2.txt")
  }
}