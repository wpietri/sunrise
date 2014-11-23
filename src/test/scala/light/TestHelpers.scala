package light

import com.github.nscala_time.time.StaticLocalTime

trait TestHelpers {
  def time(hours: Int, minutes: Int = 0) = StaticLocalTime.fromMillisOfDay((hours * 60 + minutes) * 60 * 1000)

}
