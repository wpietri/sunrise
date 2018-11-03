package app

import org.joda.time._
import org.joda.time.format.DateTimeFormatterBuilder

import scala.concurrent.duration._
import scala.util.Properties


// A simple container for settings. Possibly refactoring toward this: https://github.com/typesafehub/config#schemas-and-validation

object Settings {

  private val timeParser = new DateTimeFormatterBuilder().appendHourOfDay(1).appendLiteral(":").appendMinuteOfHour(2).toFormatter

  val localTimeZone = DateTimeZone.forID(Properties.envOrElse("SUNRISE_TIMEZONE", "America/Los_Angeles"))

  val dawnStart = timeParser.parseLocalTime(Properties.envOrElse("SUNRISE_DAWN_START", "6:00"))
  val dawnLength = Period.minutes((60 * Properties.envOrElse("SUNRISE_DAWN_LENGTH", "3").toFloat).toInt)

  val duskStart = timeParser.parseLocalTime(Properties.envOrElse("SUNRISE_DUSK_START", "16:00"))
  val duskLength = Period.minutes((60 * Properties.envOrElse("SUNRISE_DUSK_LENGTH", "6").toFloat).toInt)

  val updateFrequency = 15.seconds

  val bridgeAddress = System.getenv("SUNRISE_HUE_HOST") // e.g. "192.168.1.81"
  val bridgePort = Properties.envOrElse("SUNRISE_HUE_PORT", "80").toInt
  val bridgeKey = System.getenv("SUNRISE_HUE_KEY") // e.g., "080ed655b6f74144a29fd2f256ef73ae"
}