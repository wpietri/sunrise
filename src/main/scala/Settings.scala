import org.joda.time._

import scala.concurrent.duration._


// A simple container for settings. Possibly refactoring toward this: https://github.com/typesafehub/config#schemas-and-validation

object Settings {
  val localTimeZone = DateTimeZone.forID("America/Los_Angeles")

  val dawnStart = new LocalTime(6, 0)
  val dawnLength = new Period(3, PeriodType.hours())
  val sunsetStart = new LocalTime(18, 30)
  val sunsetLength = new Period(3, PeriodType.hours())

  val updateFrequency = 15.seconds

  val bridgeAddress = "192.168.1.81"
  val bridgePort = 80
  val bridgeKey = "080ed655b6f74144a29fd2f256eff3ae"

}