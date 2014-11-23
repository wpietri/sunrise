package light

import org.scalatest._
import org.scalatest.matchers.ShouldMatchers

class LightOutputTest extends FlatSpec with ShouldMatchers with LightMatchers {


  "Luminosity addition" should "work" in {
    LightOutput(0.3, 0.3, 100) + LightOutput(0.3, 0.3, 100) should equal(LightOutput(0.3, 0.3, 200))
  }

  "Luminosity subtraction" should "work" in {
    LightOutput(0.3, 0.3, 200) - LightOutput(0.3, 0.3, 100) should equal(LightOutput(0.3, 0.3, 100))
  }

  "equality" should "work" in {
    LightOutput(0.3, 0.3, 100) should equal(LightOutput(0.3, 0.3, 100))
  }

  "Creation from a calculator result" should "work" in {
    LightOutput(ColorTemperatureCurve(6500), 100) should equal(LightOutput(0.310, 0.317, 100))
  }

}
