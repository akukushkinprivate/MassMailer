package models

import play.api.libs.json._

case class Dispatch(var id: Long, message: String, addresses: Array[String])

object Dispatch {
  implicit val DispatchFormat = Json.format[Dispatch]
}