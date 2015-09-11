package study.spiral

trait Direction {
  def symbol(): String

  def next(): Direction

  def difference(): (Int, Int)
}

case class West() extends Direction {
  override def symbol() = "-"

  override def next() = new North

  override def difference() = (-1, 0)
}

case class South() extends Direction {
  override def symbol(): String = "|"

  override def next(): Direction = new West

  override def difference() = (0, -1)
}

case class East() extends Direction {
  override def symbol(): String = "-"

  override def next(): Direction = new South

  override def difference() = (1, 0)
}

case class North() extends Direction {
  override def symbol(): String = "|"

  override def next(): Direction = new East

  override def difference() = (0, 1)
}

