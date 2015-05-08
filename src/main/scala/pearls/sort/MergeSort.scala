package pearls.sort

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.ArrayBuffer

object MergeSort {
  @annotation.tailrec
  def merge(merged: ArrayBuffer[Int], leftValues: Array[Int], rightValues: Array[Int]): Array[Int] = 
    (leftValues, rightValues) match {
    case (Array(), _) => (merged ++= rightValues.map(x => x.toInt)).toArray
    case (_, Array()) => (merged ++= leftValues.map(x => x.toInt)).toArray
    case (Array(x, _*), Array(y, _*)) => {
      if (x < y) merge(merged += x, leftValues.tail, rightValues)
      else merge(merged += y, leftValues, rightValues.tail)
    }
  }
  
  def sort(values: Array[Int]): Array[Int] = values match {
    case Array(x) => Array(x)
    case Array(x, y) => if (x > y) Array(y, x) else Array(x, y)
    case _ => {
      println(values.foldLeft("")((s, v) => s + v + ","))
      val center = values.length / 2
      val left = sort(values.take(center))
      val right = sort(values.drop(center))
      
      merge(ArrayBuffer(), left, right)   
    }
  }
  
  def main(args: Array[String]): Unit = {
    val sortedValues = sort(Array(3, 2, 6, 0, 9, 8, 1))
    println(sortedValues.foldLeft("")((s, v) => s + v + ","))
  }
}