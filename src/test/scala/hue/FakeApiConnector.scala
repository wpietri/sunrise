package hue

import play.api.libs.json.{JsArray, JsObject}

class FakeApiConnector extends ApiConnector {
  var nextGetResponse: JsObject = null

  var nextPutResponse: JsArray = null
  var lastPath: String = null
  var lastData: JsObject = null
  val getResponses = scala.collection.mutable.Map[String, JsObject]()

  def addResponse(path: String, data: JsObject) = getResponses("/api/dummykey" + path) = data

  override def get(path: String): JsObject = {
    print(this)
    lastPath = path
    if (nextGetResponse == null) {
      getResponses(path)
    } else {
      val result = nextGetResponse
      nextGetResponse = null
      result
    }
  }

  override def put(path: String, data: JsObject): JsArray = {
    lastPath = path
    lastData = data
    nextPutResponse
  }
}
