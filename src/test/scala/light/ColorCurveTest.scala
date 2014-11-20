package light

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class ColorCurveTest extends FlatSpec with ShouldMatchers {

  "The given points" should "be correct" in {
    val c = new ColorCurve()
    c(0.251) should be(0.251 plusOrMinus 0.001)
    c(0.315) should be(0.322 plusOrMinus 0.001)
    c(0.400) should be(0.387 plusOrMinus 0.001)
    c(0.446) should be(0.407 plusOrMinus 0.001)
    c(0.538) should be(0.412 plusOrMinus 0.001)
    c(0.653) should be(0.342 plusOrMinus 0.001)
  }

  "The interpolated points" should "also be correct" in {
    val c = new ColorCurve()
    c(0.350) should be(0.358 plusOrMinus 0.005)
    c(0.584) should be(0.393 plusOrMinus 0.005)
  }


}
