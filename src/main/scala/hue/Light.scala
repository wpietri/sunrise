package hue

import light.LightOutput
import play.api.libs.json.JsValue

import scala.collection.mutable.ListBuffer
import scala.concurrent.duration.{FiniteDuration, _}


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

  def set(output: LightOutput, duration: FiniteDuration = 1.seconds): Unit = {
    set(parametersForLightOutput(closestOutput(output), duration): _*)
  }

  private def parametersForLightOutput(output: LightOutput, duration: FiniteDuration): Seq[HueParameter] = {
    val params = new ListBuffer[HueParameter]
    if (on && output.lumens <= 0) params += On(false)
    else if (!on && output.lumens > 0) params += On(true)

    if (output.lumens > 0) {
      params += Color(output.x, output.y)
      params += Brightness((255 * output.lumens / maxLumens).toInt)
    }
    params += TransitionTime(duration)
    params.seq
  }


  override def toString = "Light(" + number + ", " + model + ")"

  override def compare(that: Light): Int = this.number - that.number
}
