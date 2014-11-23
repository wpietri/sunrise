package light

import org.joda.time.LocalTime

/* The bright light of day. Ignoring color and brightness variations for simplicity. */
class Day extends LightProgram {
  val daylightColor = ColorTemperatureCurve(6500)

  override def apply(t: LocalTime): LightOutput = new LightOutput(daylightColor._1, daylightColor._2, 5000)
}
