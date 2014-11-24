package light

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class DefaultLightProgramTest extends FlatSpec with ShouldMatchers with TestHelpers with LightMatchers {
  "The result" should "be bright and cool to warm and dim" in {
    val d = new DefaultLightProgram(time(6), period(3), time(16), period(6))
    d(time(0)) should equal(LightOutput(1000, 0))
    d(time(6)) should equal(LightOutput(1000, 0))
    d(time(7, 30)) should equal(LightOutput(2700, 2000))
    d(time(9)) should equal(LightOutput(6500, 5000))
    d(time(16)) should equal(LightOutput(6500, 5000))
    d(time(19)) should equal(LightOutput(2500, 1500))
    d(time(22)) should equal(LightOutput(1000, 0))
    d(time(23, 59)) should equal(LightOutput(1000, 0))
  }

}


