import play.api.libs.json.{JsObject, Json}

import scala.concurrent.duration.FiniteDuration

trait HueParameter {
  def toJsObject: JsObject
}


case class Color(x: Double, y: Double) extends HueParameter {
  override implicit def toJsObject: JsObject = Json.obj("xy" -> Json.arr(x, y))

  override def toString: String = f"Color($x%1.3f,$y%1.3f)"
}

case class On(on: Boolean) extends HueParameter {
  override implicit def toJsObject: JsObject = Json.obj("on" -> on)
}

case class Brightness(b: Int) extends HueParameter {
  require(b >= 0)
  require(b < 256)

  override implicit def toJsObject: JsObject = Json.obj("bri" -> b)
}

case class TransitionTime(duration: FiniteDuration) extends HueParameter {

  def cap(n: Long, i: Int): Long = if (n > i) i else n

  override implicit def toJsObject: JsObject = Json.obj("transitiontime" -> cap(duration.toMillis / 100, 65535))
}

