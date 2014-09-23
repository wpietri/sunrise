import play.api.libs.json._


trait ApiConnector {
  def get(path: String): JsObject

  def put(path: String, data: JsObject): JsArray
}
