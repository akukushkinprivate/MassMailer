package controllers

import javax.inject.{Inject, Singleton}

import Actors.SaveStatusActor
import Actors.SaveStatusActor.SaveStatus
import akka.actor.ActorSystem
import models.Dispatch
import play.api.libs.json.JsValue
import play.api.mvc.{AbstractController, Action, ControllerComponents}
import play.mvc.Result
import akka.pattern.ask

import scala.collection.mutable
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class DispatchController @Inject()(cc: ControllerComponents, actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends AbstractController(cc) {
  val statuses = new mutable.HashMap[String, String]

  def create(json: JsValue): Action[JsValue] = Action.async(parse.json) { implicit request =>
    val dispatch = json.as[Dispatch]
    val hash = dispatch.hashCode.toString
    statuses += (hash -> null)
    val saveStatusActor = actorSystem.actorOf(SaveStatusActor.props)
    Future {
      Ok(hash)
    }
  }

  def getStatus: Result = play.mvc.Results.TODO
}
