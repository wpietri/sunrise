package light

import org.joda.time.LocalTime

trait DaylightCurve {
  val MillisPerDay = 24 * 60 * 60 * 1000

  val interpolator: breeze.interpolation.UnivariateInterpolator[Double]

  def timeAsFractionOfDay(t: LocalTime): Double = {
    1.0 * t.millisOfDay().get() / MillisPerDay
  }

  def t(hours: Int, minutes: Int = 0): Double = (60 * hours + minutes) / (60.0 * 24)

  def apply(t: LocalTime): Double = {
    interpolator(timeAsFractionOfDay(t))
  }

}
