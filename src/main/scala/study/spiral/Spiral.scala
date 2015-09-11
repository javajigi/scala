package study.spiral


class Spiral {
  def createSprialArray(spiral: List[String], length: Int, direction: Direction): List[String] = {
    if (length == 0) spiral
    else {
      val newSpiral = spiral ::: goDirection(length, direction)
      createSprialArray(newSpiral, length - 1, direction.next())
    }
  }

  def goDirection(length: Int, direction:Direction) = {
    (1 to length).map({
      case i if (i == length) => "+"
      case _ => direction.symbol()
    }).toList
  }
}
