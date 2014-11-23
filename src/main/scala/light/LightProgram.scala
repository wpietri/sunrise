package light

import org.joda.time.LocalTime

abstract class LightProgram {
  def apply(t: LocalTime): LightOutput

}
