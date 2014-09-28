import play.api.libs.json.JsValue

class Light(val bridge: Bridge, number: Integer) extends HueDevice {


  def path: String = "/lights/" + number + "/state"

  def on: Boolean = (fetchLightInfo \ "state" \ "on").as[Boolean]

  def model = (fetchLightInfo \ "modelid").as[String]

  def fetchLightInfo: JsValue = bridge.get("/lights/" + number)

}
