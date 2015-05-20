package study.first

import scala.io.Source

object FileReader {
  def showLines(file: String): Unit = {
    def widthOfLength(s: String) = s.length.toString.length

    def padding(maxWidth: Int, widthOfLength: Int): String = {
      val numSpaces = maxWidth - widthOfLength
      " " * numSpaces
    }

    val lines = Source.fromFile(file).getLines().toList
    val longestLine = lines.reduceLeft(
      (a, b) => if (a.length > b.length) a else b)
    val maxWidth = widthOfLength(longestLine)

    val linesWithLength = lines.map { line =>
      padding(maxWidth, widthOfLength(line)) + line.length + " | " + line
    }

    linesWithLength.foreach { println }
  }

  def main(args: Array[String]): Unit = {
    showLines("src/main/scala/study/FileReader.scala")
  }
}