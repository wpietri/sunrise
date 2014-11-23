package light

import com.github.nscala_time.time.StaticLocalTime
import org.joda.time.Period

trait TestHelpers {
  def time(hours: Int, minutes: Int = 0) = StaticLocalTime.fromMillisOfDay((hours * 60 + minutes) * 60 * 1000)

  def period(hours: Int, minutes: Int = 0): Period = Period.hours(hours).plus(Period.minutes(minutes))

}
