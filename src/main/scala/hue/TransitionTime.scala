package hue

import play.api.libs.json.{JsObject, Json}

import scala.concurrent.duration.FiniteDuration

case class TransitionTime(duration: FiniteDuration) extends HueParameter {

  def cap(n: Long, i: Int): Long = if (n > i) i else n

  override implicit def toJsObject: JsObject = Json.obj("transitiontime" -> cap(duration.toMillis / 100, 65535))
}
