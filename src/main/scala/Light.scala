import play.api.libs.json.JsValue

class Light(val bridge: Bridge, number: Integer) extends HueDevice {

  def path: String = {
    "/lights/" + number + "/state"
  }

  def on(): Boolean = {
    val json: JsValue = bridge.get("/lights/" + number)
    (json \ "state" \ "on").as[Boolean]
  }
}
