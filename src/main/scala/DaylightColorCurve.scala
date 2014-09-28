import breeze.interpolation._
import breeze.linalg.DenseVector
import org.joda.time.LocalTime

object DaylightColorCurve {
  val MillisPerDay = 24 * 60 * 60 * 1000

  val interpolator = new LinearInterpolator(
    DenseVector(Array(
      t(0), t(6, 15), t(7), t(9),
      t(12),
      t(16), t(19), t(20, 30), t(23, 59))),
    DenseVector(Array[Double](
      1000, 1000, 3000, 6500,
      20000,
      6500, 3000, 1000, 1000))
  )

  def apply(t: LocalTime) = {
    interpolator(timeAsFractionOfDay(t))
  }

  def timeAsFractionOfDay(t: LocalTime): Double = {
    1.0 * t.millisOfDay().get() / MillisPerDay
  }

  def t(hours: Int, minutes: Int = 0): Double = (60 * hours + minutes) / (60.0 * 24)

}
