package hue

import play.api.libs.json.{JsObject, Json}

case class Brightness(b: Int) extends HueParameter {
  require(b >= 0)
  require(b < 256)

  override implicit def toJsObject: JsObject = Json.obj("bri" -> b)
}
