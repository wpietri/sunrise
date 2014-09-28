import breeze.interpolation._
import breeze.linalg.DenseVector
import org.joda.time.LocalTime

/*
Hue bulb ratings

550 lm @ 6500K
600 lm @ 4000K
510 lm @ 3000K
360 lm @ 2000K
*/

/** Maps time to desired lumen levels */
object DaylightLumensCurve extends DaylightCurve {
  val interpolator = new LinearInterpolator(
    DenseVector(Array(
      t(0), t(6, 15), t(7), t(9),
      t(12),
      t(16), t(19), t(20, 30), t(23, 59))),
    DenseVector(Array[Double](
      0, 1, 2000, 5000,
      10000,
      5000, 2500, 1, 0))
  )

  override def apply(t: LocalTime) = {
    interpolator(timeAsFractionOfDay(t))
  }


}
