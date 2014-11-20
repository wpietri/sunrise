package light

import org.scalatest._
import org.scalatest.matchers.ShouldMatchers

class LightOutputTest extends FlatSpec with ShouldMatchers {


  "Luminosity addition" should "work" in {
    LightOutput(0.3, 0.3, 100) + LightOutput(0.3, 0.3, 100) should be(LightOutput(0.3, 0.3, 200))
  }

  "Luminosity subtraction" should "work" in {
    LightOutput(0.3, 0.3, 200) - LightOutput(0.3, 0.3, 100) should be(LightOutput(0.3, 0.3, 100))
  }

}
