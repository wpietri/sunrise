import play.api.libs.json.JsObject

class FakeApiConnector extends ApiConnector {
  var nextResponse: JsObject = null
  var lastPath: String = null

  override def get(path: String): JsObject = {
    lastPath = path
    nextResponse
  }

  override def put(path: String, data: JsObject): JsObject = ???
}
