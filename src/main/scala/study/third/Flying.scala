package study.third

trait Flying {
  def flyMessage: String
  def fly() = println(flyMessage)
}