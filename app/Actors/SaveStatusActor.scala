package Actors

import akka.actor.{Actor, Props}

object SaveStatusActor {
  def props = Props[SaveStatusActor]

  case class SaveStatus(id: String, status: String)

}

class SaveStatusActor extends Actor {

  import SaveStatusActor._

  override def receive: Receive = {
    case SaveStatus(id: String, status: String) => ???
  }
}
