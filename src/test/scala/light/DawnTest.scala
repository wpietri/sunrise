package light

import app.Settings
import app.Settings._
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class DawnTest extends FlatSpec with ShouldMatchers with TestHelpers with LightMatchers {
  "The result" should "be warm and dim to bright and cool" in {
    val d = new Dawn(time(6), period(3))
    d(time(0)) should equal(LightOutput(1000, 0))
    d(time(6)) should equal(LightOutput(1000, 0))
    d(time(7, 30)) should equal(LightOutput(2700, 2000))
    d(time(9)) should equal(LightOutput(6500, 5000))
    d(time(18)) should equal(LightOutput(6500, 5000))
  }

  "The program" should "be buildable from settings" in {
    val d = new Dawn(dawnStart, dawnLength)
  }

}


