import play.api.libs.json.{JsObject, Json}

trait HueDevice {

  def bridge: Bridge

  def path: String

  def set(parameters: HueParameter*): Unit = {
    bridge.put(path, toJson(parameters))
  }

  def toJson(parameters: Seq[HueParameter]): JsObject = {
    parameters.foldLeft(Json.obj())((j, b) => j.deepMerge(b.toJsObject))
  }

}
