package light

import org.joda.time.{LocalTime, Period}

class DefaultLightProgram(dawnStart: LocalTime, dawnDuration: Period,
                          duskStart: LocalTime, duskDuration: Period) extends LightProgram {
  var dawnEnd = dawnStart.plus(dawnDuration)
  var duskEnd = duskStart.plus(duskDuration)
  var dawn = new Dawn(dawnStart, dawnDuration)
  var day = new Day()
  var dusk = new Dusk(duskStart, duskDuration)
  var night = new Night()

  override def apply(t: LocalTime): LightOutput = {
    if (t.isBefore(dawnStart)) night(t)
    else if (t.isBefore(dawnEnd)) dawn(t)
    else if (t.isBefore(duskStart)) day(t)
    else if (t.isBefore(duskEnd)) dusk(t)
    else night(t)
  }
}
