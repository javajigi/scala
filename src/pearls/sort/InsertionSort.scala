package pearls.sort

object InsertionSort {
  def sort(values: Array[Int]): Array[Int] = {
    for (i <- 1 until values.length) {
    	val currentVal = values(i)
      var position = i - 1
      while (position >= 0 && values(position) > currentVal) {
    	  values(position + 1) = values(position)
 			  position = position - 1
      }
      values(position + 1) = currentVal
    }
    values
  }
  
  def main(args: Array[String]): Unit = {
    val sortedValues = sort(Array(3, 2, 6, 0, 9, 8, 1))
    println(sortedValues.foldLeft("")((s, v) => s + v + ","))
  }
}