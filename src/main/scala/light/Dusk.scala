package light

import light.DaylightColorCurve._
import org.joda.time.{LocalTime, Period}

class Dusk(start: LocalTime, duration: Period) extends LightProgram {
  val times = Array(MIDNIGHT, start, start.plus(mult(0.5, duration)), start.plus(duration), END_OF_DAY).map(t => timeAsFractionOfDay(t))

  val lumens = interpolator(times, Array[Double](5000, 5000, 1000, 0, 0))
  val colors = interpolator(times, Array[Double](6500, 6500, 2500, 1000, 1000))

  override def apply(t: LocalTime): LightOutput = {
    val dayFraction = timeAsFractionOfDay(t)
    LightOutput(colors(dayFraction), lumens(dayFraction))
  }
}
