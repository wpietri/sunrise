package light

import light.DaylightColorCurve._
import org.joda.time.{LocalTime, Period}

class Dawn(start: LocalTime, duration: Period) extends LightProgram {
  val times = Array(MIDNIGHT, start, start.plus(mult(0.5, duration)), start.plus(duration), END_OF_DAY).map(t => timeAsFractionOfDay(t))

  val lumens = interpolator(times, Array[Double](0, 0, 1000, 5000, 5000))
  val colors = interpolator(times, Array[Double](1000, 1000, 2700, 6500, 6500))


  override def apply(t: LocalTime): LightOutput = {
    val dayFraction = timeAsFractionOfDay(t)
    LightOutput(colors(dayFraction), lumens(dayFraction))
  }
}
