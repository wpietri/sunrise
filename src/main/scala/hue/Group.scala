package hue

import play.api.libs.json.{JsArray, JsString}

class Group(val bridge: Bridge, val number: Int) extends HueDevice {

  override def path: String = s"/groups/$number"

  def lights: Seq[Light] = bridge.get("/groups/" + number) \ "lights" match {
    case a: JsArray => a.value.map { case s: JsString => bridge.light(s.value.toInt) }
    case _ => throw new ClassCastException
  }


  override def toString = s"Group($number)"
}
