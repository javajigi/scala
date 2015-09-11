package study.spiral

trait Direction {
  def symbol(): String

  def next(): Direction
}

case class West() extends Direction {
  override def symbol() = "-"

  override def next() = new South
}

case class South() extends Direction {
  override def symbol(): String = "|"

  override def next(): Direction = new East
}

case class East() extends Direction {
  override def symbol(): String = "-"

  override def next(): Direction = new North
}

case class North() extends Direction {
  override def symbol(): String = "|"

  override def next(): Direction = new West
}

