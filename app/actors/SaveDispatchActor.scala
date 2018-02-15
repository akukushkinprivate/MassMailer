package actors

import akka.actor.{Actor, Props}

object SaveDispatchActor {
  private var autoIncrementedId = 0l

  def props: Props = Props[SaveDispatchActor]

  case class SaveDispatch()
}

class SaveDispatchActor extends Actor {

  import SaveDispatchActor._

  override def receive: Receive = {
    case SaveDispatch =>
      autoIncrementedId += 1
      sender ! autoIncrementedId
  }
}
