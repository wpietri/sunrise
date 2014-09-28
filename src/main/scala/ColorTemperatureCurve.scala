import breeze.interpolation._
import breeze.linalg.DenseVector

// made from manual measurements of a CIE 1931 diagram.

// a more formal mathematical version could be taken from http://en.wikipedia.org/wiki/Planckian_locus

class ColorTemperatureCurve {
  val tempToX = new CubicInterpolator(
    DenseVector(Array(1000.0, 2366.0, 3600.0, 4800.0, 10000.0, 20000.0)),
    DenseVector(Array(0.653, 0.490, 0.400, 0.351, 0.281, 0.258))
  )
  val xToY = new ColorCurve

  def apply(tempK: Int) = {
    val x = tempToX(tempK)
    val y = xToY.y(x)
    (x, y)
  }
}
