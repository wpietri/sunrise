import play.api.libs.json.{JsValue, Json}

class Light(bridge: Bridge, number: Integer) {

  def set(p: HueParameter*): Unit = {
    println(p.getClass)
    val combinedJson = p.foldLeft(Json.obj())((j, b) => j.deepMerge(b.toJsObject))
    bridge.put("/lights/" + number + "/state", combinedJson)
  }


  def on(): Boolean = {
    val json: JsValue = bridge.get("/lights/" + number)
    (json \ "state" \ "on").as[Boolean]
  }
}
