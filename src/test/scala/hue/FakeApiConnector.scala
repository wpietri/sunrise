package hue

import play.api.libs.json.{JsArray, JsObject}

class FakeApiConnector extends ApiConnector {
  var nextGetResponse: JsObject = null
  var nextPutResponse: JsArray = null
  var lastPath: String = null
  var lastData: JsObject = null

  override def get(path: String): JsObject = {
    lastPath = path
    nextGetResponse
  }

  override def put(path: String, data: JsObject): JsArray = {
    lastPath = path
    lastData = data
    nextPutResponse
  }
}
