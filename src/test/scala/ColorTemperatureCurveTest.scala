import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class ColorTemperatureCurveTest extends FlatSpec with ShouldMatchers {

  "Warm end" should "be correct" in {
    val c = new ColorTemperatureCurve()
    val (x, y) = c(1000)
    x should be(0.653 plusOrMinus 0.005)
    y should be(0.342 plusOrMinus 0.005)
  }

  "Mid-warm" should "be correct" in {
    val c = new ColorTemperatureCurve()
    val (x, y) = c(1900)
    x should be(0.538 plusOrMinus 0.005)
    y should be(0.411 plusOrMinus 0.005)
  }

  "Middle" should "be correct" in {
    val c = new ColorTemperatureCurve()
    val (x, y) = c(3600)
    x should be(0.400 plusOrMinus 0.005)
    y should be(0.387 plusOrMinus 0.005)
  }

  "Mid-cold" should "be correct" in {
    val c = new ColorTemperatureCurve()
    val (x, y) = c(6500)
    x should be(0.314 plusOrMinus 0.005)
    y should be(0.313 plusOrMinus 0.005)
  }



  "Cold end" should "be correct" in {
    val c = new ColorTemperatureCurve()
    val (x, y) = c(20000)
    x should be(0.258 plusOrMinus 0.005)
    y should be(0.258 plusOrMinus 0.005)
  }


}
