package app

import org.joda.time._

import scala.concurrent.duration._


// A simple container for settings. Possibly refactoring toward this: https://github.com/typesafehub/config#schemas-and-validation

object Settings {
  val localTimeZone = DateTimeZone.forID("America/Los_Angeles")

  val dawnStart = new LocalTime(6, 0)
  val dawnLength = Period.hours(3)
  val duskStart = new LocalTime(16, 00)
  val duskLength = Period.hours(6)

  val updateFrequency = 15.seconds

  val bridgeAddress = "192.168.1.81"
  val bridgePort = 80
  val bridgeKey = "080ed655b6f74144a29fd2f256eff3ae"
}