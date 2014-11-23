package light

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class DayTest extends FlatSpec with ShouldMatchers with TestHelpers with LightMatchers {
  "The result" should "always be bright and cold white" in {
    val d = new Day
    val daylight = LightOutput(ColorTemperatureCurve(6500), 5000)
    d(time(0)) should equal(daylight)
    d(time(6)) should equal(daylight)
    d(time(12)) should equal(daylight)
    d(time(18)) should equal(daylight)
  }

}


