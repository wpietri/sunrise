package light

import org.joda.time.LocalTime

class Night extends LightProgram {
  val nightColor = ColorTemperatureCurve(1000)

  override def apply(t: LocalTime): LightOutput = new LightOutput(nightColor._1, nightColor._2, 0)
}
