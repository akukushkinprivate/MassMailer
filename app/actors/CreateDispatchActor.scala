package actors

import javax.inject.Inject

import akka.actor.{Actor, Props}
import models.Dispatch
import play.api.libs.ws.{WSClient, WSRequest, WSResponse}

import scala.concurrent.Future
import scala.concurrent.duration._

object CreateDispatchActor {
  def props: Props = Props[CreateDispatchActor]

  case class CreateDispatch(dispatch: Dispatch)

}

class CreateDispatchActor @Inject()(ws: WSClient) extends Actor {

  import CreateDispatchActor._

  override def receive: Receive = {
    case CreateDispatch(dispatch: Dispatch) =>
      val request: WSRequest = ws.url("http://smsc.ru/sys/jobs.php")
      val futureResponse : Future[WSResponse] = request.addQueryStringParameters("login" -> "test")
        .addQueryStringParameters("psw" -> "test")
        .addQueryStringParameters("name" -> "name")
        .addQueryStringParameters("phones" -> dispatch.addresses.mkString(";"))
        .addQueryStringParameters("mes" -> dispatch.message)
        .addQueryStringParameters("id" -> dispatch.id.toString)
        .withRequestTimeout(10000.millis)
        .get()
      sender ! futureResponse.mapTo[WSResponse].map(response => response)
  }
}
