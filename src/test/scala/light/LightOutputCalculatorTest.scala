package light

import hue.{Bridge, FakeApiConnector}
import org.scalatest.{FlatSpec, ShouldMatchers}
import play.api.libs.json.Json

class LightOutputCalculatorTest extends FlatSpec with ShouldMatchers {
  def lightsForModels(models: String*) = {
    models.map(model => {
      val f = new FakeBulb
      f.api.addResponse("/lights/1", Json.obj("modelid" -> model))
      f.light
    })
  }

  class FakeBulb {
    val api = new FakeApiConnector
    val light = new Bridge(api, "dummykey").light(1)
  }

  "Darkness" should "have everything off" in {
    val lights = lightsForModels("LCT001")
    LightOutputCalculator(lights, LightOutput(0, 0, 0)) should be(Map(
      (lights(0), LightOutput(0, 0, 0))
    ))
  }

  "Low light" should "have the dimmest bulb on" in {
    val lights = lightsForModels("LCT001", "LST001")
    LightOutputCalculator(lights, LightOutput(0.65, 0.32, 10)) should be(Map(
      (lights(0), LightOutput(0.65, 0.32, 0)),
      (lights(1), LightOutput(0.65, 0.32, 10))
    ))
  }

  "Light between bulb abilities" should "still use one bulb" in {
    val lights = lightsForModels("LCT001", "LST001")
    LightOutputCalculator(lights, LightOutput(0.65, 0.32, 130)) should be(Map(
      (lights(0), LightOutput(0.65, 0.32, 0)),
      (lights(1), LightOutput(0.65, 0.32, 120))
    ))
  }

  "Light requiring two bulbs" should "use two bulbs, favoring the low light bulb" in {
    val lights = lightsForModels("LCT001", "LST001")
    LightOutputCalculator(lights, LightOutput(0.65, 0.32, 200)) should be(Map(
      (lights(0), LightOutput(0.65, 0.32, 80)),
      (lights(1), LightOutput(0.65, 0.32, 120))
    ))
  }


  "Light requiring more bulbs than available" should "do what they can" in {
    val lights = lightsForModels("LCT001", "LST001")
    LightOutputCalculator(lights, LightOutput(0.65, 0.32, 1000)) should be(Map(
      (lights(0), LightOutput(0.65, 0.32, 600)),
      (lights(1), LightOutput(0.65, 0.32, 120))
    ))
  }

  "Multiple bulbs" should "prefer the strip and the multi-color bulb over the fixed-color bulb" in {
    val lights = lightsForModels("LCT001", "LCT001", "LWB004", "LST001")
    LightOutputCalculator(lights, LightOutput(0.65, 0.32, 1400)) should be(Map(
      (lights(0), LightOutput(0.65, 0.32, 600)),
      (lights(1), LightOutput(0.65, 0.32, 600)),
      (lights(2), LightOutput(0.65, 0.32, 80)),
      (lights(3), LightOutput(0.65, 0.32, 120))
    ))
  }


}
