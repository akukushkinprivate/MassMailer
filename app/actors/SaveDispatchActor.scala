package actors

import javax.inject.Inject

import akka.actor.{Actor, Props}
import models.{DispatchRepository, DispatchStatus}

object SaveDispatchActor {

  def props: Props = Props[SaveDispatchActor]

  case class InsertOrUpdateDispatchStatus(dispatchStatus: DispatchStatus)

}

class SaveDispatchActor @Inject()(dispatchRepository: DispatchRepository) extends Actor {

  import SaveDispatchActor._

  override def receive: Receive = {
    case InsertOrUpdateDispatchStatus(dispatchStatus: DispatchStatus) => sender ! dispatchRepository.insertOrUpdate(dispatchStatus)
  }
}
