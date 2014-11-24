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

  override def apply(t: LocalTime): LightOutput = currentProgram(t)(t)

  def currentProgram(t: LocalTime): LightProgram = {
    if (t.isBefore(dawnStart)) night
    else if (t.isBefore(dawnEnd)) dawn
    else if (t.isBefore(duskStart)) day
    else if (t.isBefore(duskEnd)) dusk
    else night

  }
}
