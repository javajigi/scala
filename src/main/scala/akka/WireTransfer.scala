package akka

import akka.actor.Actor.Receive
import akka.actor.{Actor, ActorRef}

object WireTransfer {
  case class Transfer(from: ActorRef, to: ActorRef, amount: BigInt)
  case object Done
  case object Failed
}

class WireTransfer extends Actor {
  import WireTransfer._

  def receive = {
    case Transfer(from, to, amount) =>
      println(s"${from}, ${to}, amount : ${amount}")
      from ! BankAccount.Withdraw(amount)
      context.become(awaitWithdraw(to, amount, sender))
  }

  def awaitWithdraw(to: ActorRef, amount: BigInt, sender: ActorRef): Receive = {
    case BankAccount.Done =>
      to ! BankAccount.Deposit(amount)
      context.become(awaitDeposit(sender))
    case BankAccount.Failed =>
      sender ! Failed
      context.stop(self)
  }

  def awaitDeposit(sender: ActorRef): Actor.Receive = {
    case BankAccount.Done =>
      sender ! Done
      context.stop(self)
  }
}
