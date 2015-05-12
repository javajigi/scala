package study

import java.io.File

import scala.io.Source

object FileMatcher {
  private def listFiles = new File("./resources/study").listFiles()
  
  private def filesMatching(matcher: String => Boolean) = {
    listFiles.filter { file => matcher(file.getName) }
  }
  
  def endsWith(query: String): Array[File] = {
    filesMatching(_.endsWith(query))
  }

  def startsWith(query: String): Array[File] = {
    filesMatching(_.startsWith(query))
  }

  def contains(query: String): Array[File] = {
    filesMatching(_.contains(query))
  }

  def main(args: Array[String]): Unit = {
    endsWith("txt").foreach( file => println("ends with : " + file) )
    startsWith("slipp").foreach( file => println("starts with : " + file) )
    contains("slipp").foreach( file => println("contains with : " + file))
  }
}