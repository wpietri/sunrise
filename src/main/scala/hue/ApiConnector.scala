package hue

import play.api.libs.json.{JsArray, JsObject}

trait ApiConnector {
  def get(path: String): JsObject

  def put(path: String, data: JsObject): JsArray
}
