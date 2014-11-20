package hue

import play.api.libs.json.JsObject

trait HueParameter {
  def toJsObject: JsObject
}
