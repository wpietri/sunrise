import play.api.libs.json.{JsObject, Json}

trait HueParameter {

  def toJsObject: JsObject
}


class Color(x: Double, y: Double) extends HueParameter {
  override implicit def toJsObject: JsObject = Json.obj("xy" -> Json.arr(x, y))
}

