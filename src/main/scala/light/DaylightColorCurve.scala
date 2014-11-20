package light

import breeze.interpolation._
import breeze.linalg.DenseVector
import org.joda.time.LocalTime

object DaylightColorCurve extends DaylightCurve {

  val interpolator = new LinearInterpolator(
    DenseVector(Array(
      t(0), t(6, 15), t(7), t(9),
      t(12),
      t(16), t(19), t(21, 30), t(23, 59))),
    DenseVector(Array[Double](
      1000, 1000, 3000, 6500,
      20000,
      6500, 2500, 1000, 1000))
  )

  override def apply(t: LocalTime): Double = {
    interpolator(timeAsFractionOfDay(t))
  }

}
