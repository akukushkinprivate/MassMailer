package controllers

import javax.inject.{Inject, Singleton}

import actors.SaveDispatchActor.SaveDispatch
import actors.{CreateDispatchActor, SaveDispatchActor}
import akka.actor.ActorSystem
import akka.pattern.ask
import models.Dispatch
import play.api.libs.json.JsValue
import play.api.mvc.{AbstractController, Action, ControllerComponents}

import scala.concurrent.ExecutionContext

@Singleton
class DispatchController @Inject()(cc: ControllerComponents, actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends AbstractController(cc) {
  def create(json: JsValue): Action[JsValue] = Action.async(parse.json) { implicit request =>
    val dispatch = json.as[Dispatch]
    val createDispatchActor = actorSystem.actorOf(CreateDispatchActor.props)
    createDispatchActor ! dispatch
    val saveStatusActor = actorSystem.actorOf(SaveDispatchActor.props)
    (saveStatusActor ? SaveDispatch).mapTo[Long].map { id => Ok(id) }
  }

  def getStatus() = play.mvc.Results.TODO
}
