package light

import breeze.interpolation.LinearInterpolator
import breeze.linalg.DenseVector
import org.joda.time.{LocalTime, Period}

abstract class LightProgram {
  def apply(t: LocalTime): LightOutput

  val MIDNIGHT = LocalTime.MIDNIGHT
  val END_OF_DAY = LocalTime.MIDNIGHT.plus(Period.days(1).minus(Period.seconds(1)))

  def mult(multiplier: Double, period: Period): Period = {
    Period.seconds((multiplier * period.toStandardDuration.getStandardSeconds).toInt)
  }

  def interpolator(x: Array[Double], y: Array[Double]): LinearInterpolator[Double] = try {
    new LinearInterpolator(DenseVector(x), DenseVector(y))
  } catch {
    case e: java.lang.Exception =>
      System.err.println("failure; dumping x:")
      x.foreach(a => System.err.println(a))
      throw e
  }

}
