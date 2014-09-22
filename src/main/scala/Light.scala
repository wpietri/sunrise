import play.api.libs.json.JsValue

class Light(bridge: Bridge, number: Integer) {

  def on(): Boolean = {
    val json: JsValue = bridge.get("/lights/" + number)
    (json \ "state" \ "on").as[Boolean]
  }
}
