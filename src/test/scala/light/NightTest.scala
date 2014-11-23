package light

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class NightTest extends FlatSpec with ShouldMatchers with TestHelpers with LightMatchers {
  "The result" should "be dark and full of terrors" in {
    val d = new Night
    val nightlight = LightOutput(ColorTemperatureCurve(1000), 0)
    d(time(0)) should equal(nightlight)
    d(time(6)) should equal(nightlight)
    d(time(12)) should equal(nightlight)
    d(time(18)) should equal(nightlight)
  }

}


