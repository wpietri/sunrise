import play.api.libs.json.JsValue

class Light(val bridge: Bridge, number: Integer) extends HueDevice {


  def path: String = "/lights/" + number + "/state"

  def on: Boolean = (fetchLightInfo \ "state" \ "on").as[Boolean]

  def model = (fetchLightInfo \ "modelid").as[String]

  def fetchLightInfo: JsValue = bridge.get("/lights/" + number)

  def maxLumens: Int = model match {
    case "LCT001" => 600
    case "LST001" => 120
    case "LWB004" => 750
  }

}
