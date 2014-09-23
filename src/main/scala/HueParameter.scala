import play.api.libs.json.{JsObject, Json}

trait HueParameter {
  def toJsObject: JsObject
}


case class Color(x: Double, y: Double) extends HueParameter {
  override implicit def toJsObject: JsObject = Json.obj("xy" -> Json.arr(x, y))
}

case class On(on: Boolean) extends HueParameter {
  override implicit def toJsObject: JsObject = Json.obj("on" -> on)
}

case class Brightness(b: Int) extends HueParameter {
  require(b >= 0)
  require(b < 256)

  override implicit def toJsObject: JsObject = Json.obj("bri" -> b)
}

