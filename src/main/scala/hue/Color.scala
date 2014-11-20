package hue

import play.api.libs.json.{JsObject, Json}

case class Color(x: Double, y: Double) extends HueParameter {
  override implicit def toJsObject: JsObject = Json.obj("xy" -> Json.arr(x, y))

  override def toString: String = f"Color($x%1.3f,$y%1.3f)"
}
