package hue

import play.api.libs.json.{JsObject, Json}

case class On(on: Boolean) extends HueParameter {
  override implicit def toJsObject: JsObject = Json.obj("on" -> on)
}
