import com.github.nscala_time.time.StaticLocalTime
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class DaylightColorCurveTest extends FlatSpec with ShouldMatchers {

  "Morning" should "go from red to warm to cool" in {
    DaylightColorCurve(t(0)) should be(1000.0 plusOrMinus 10)
    DaylightColorCurve(t(6, 15)) should be(1000.0 plusOrMinus 10)
    DaylightColorCurve(t(7)) should be(3000.0 plusOrMinus 10)
    DaylightColorCurve(t(9)) should be(6500.0 plusOrMinus 10)
  }

  // honestly, not sure about this part; too blue?
  "Daytime" should "be blue" in {
    DaylightColorCurve(t(12)) should be(20000.0 plusOrMinus 10)
  }

  "Evening" should "go from cool to warm to red" in {
    DaylightColorCurve(t(16)) should be(6500.0 plusOrMinus 10)
    DaylightColorCurve(t(19)) should be(3000.0 plusOrMinus 10)
    DaylightColorCurve(t(20, 30)) should be(1000.0 plusOrMinus 10)
    DaylightColorCurve(t(23, 59)) should be(1000.0 plusOrMinus 10)
  }


  def t(hours: Int, minutes: Int = 0) = StaticLocalTime.fromMillisOfDay((hours * 60 + minutes) * 60 * 1000)

}
