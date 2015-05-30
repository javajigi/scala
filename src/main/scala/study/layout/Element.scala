package study.layout

import Element.elem
import support.DomainModel

abstract class Element extends DomainModel {
  def contents: Array[String]
  def height: Int = contents.length
  def width: Int = if (height == 0) 0 else contents(0).length

  def above(that: Element): Element =
    elem(this.contents ++ that.contents)

  def beside(that: Element): Element =
    elem(
      (this.contents zip that.contents).map(x => x._1 + x._2)
    )
  
  override def toString = contents mkString "\n"
}

object Element {
  def elem(contents: Array[String]): Element =
    new ArrayElement(contents)
  
  def elem(chr: Char, width: Int, height: Int): Element =
    new UniformElement(chr, width, height)
  
  def elem(line: String): Element = 
    new LineElement(line)
}