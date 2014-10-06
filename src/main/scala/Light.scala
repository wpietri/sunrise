import play.api.libs.json.JsValue

class Light(val bridge: Bridge, val number: Integer) extends HueDevice with Ordered[Light] {


  def path: String = "/lights/" + number + "/state"

  def on: Boolean = (fetchLightInfo \ "state" \ "on").as[Boolean]

  def minLumens = (maxLumens * 0.05).toInt

  def maxLumens: Int = model match {
    case "LCT001" => 600
    case "LST001" => 120
    case "LWB004" => 750
  }

  def model = (fetchLightInfo \ "modelid").as[String]

  def fetchLightInfo: JsValue = bridge.get("/lights/" + number)

  // for now, ignores color
  def closestOutput(desiredOutput: LightOutput): LightOutput = {
    if (desiredOutput.lumens < minLumens)
      desiredOutput.withLumens(0)
    else if (desiredOutput.lumens > maxLumens)
      desiredOutput.withLumens(maxLumens)
    else
      desiredOutput
  }


  override def toString = "Light(" + number + ", " + model + ")"

  override def compare(that: Light): Int = this.number - that.number
}
