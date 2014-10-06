import scala.collection.mutable

object LightOutputCalculator {

  def apply(lights: Seq[Light], target: LightOutput) = {
    val result = mutable.Map.empty[Light, LightOutput]
    val sortedLights = lights.sortWith(compareLights)
    var remainder = target
    for (light <- sortedLights) {
      val lightOutput = light.closestOutput(remainder)
      result(light) = lightOutput
      remainder = remainder - lightOutput
    }
    result
  }

  def compareLights(a: Light, b: Light): Boolean = {
    if (a.maxLumens == b.maxLumens)
      a.number < b.number
    else
      a.maxLumens < b.maxLumens
  }
}
