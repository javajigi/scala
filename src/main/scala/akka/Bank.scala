package akka

import akka.WireTransfer._

import akka.actor.{Props, ActorSystem}

object Bank {
  def main(args: Array[String]) {
    val system = ActorSystem("BankSystem")
    val from = system.actorOf(Props(new BankAccount(10000)), name="from")
    val to = system.actorOf(Props(new BankAccount(5000)), name="to")
    val transfer = system.actorOf(Props[WireTransfer], name="transfer")

    transfer ! Transfer(from, to, 3000)
  }
}
