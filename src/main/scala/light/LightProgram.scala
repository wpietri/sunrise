package light

import org.joda.time.{LocalTime, Period}

abstract class LightProgram {
  def apply(t: LocalTime): LightOutput

  val MIDNIGHT = LocalTime.MIDNIGHT
  val END_OF_DAY = LocalTime.MIDNIGHT.plus(Period.days(1).minus(Period.seconds(1)))

  def mult(multiplier: Double, period: Period): Period = {
    Period.seconds((multiplier * period.toStandardDuration.getStandardSeconds).toInt)
  }

}
