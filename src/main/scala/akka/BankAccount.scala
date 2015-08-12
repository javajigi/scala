package akka

import akka.actor.Actor

object BankAccount {
  case class Withdraw(amount: BigInt) {
    require(amount > 0)
  }
  case class Deposit(amount: BigInt) {
    require(amount > 0)
  }
  case object Done
  case object Failed
}

class BankAccount(a: BigInt) extends Actor {
  import BankAccount._

  var balance = a

  def receive = {
    case Withdraw(amount) if (balance >= amount) =>
      balance -= amount
      println(s"balance after withdraw : ${balance}")
      sender ! Done
    case Deposit(amount) =>
      balance += amount
      println(s"balance after deposit : ${balance}")
      sender ! Done
    case _ => sender ! Failed
  }
}
