import breeze.interpolation._
import breeze.linalg.DenseVector

/* The black body emitter curve through the CIE 1931 color space  */
class ColorCurve {
  val interpolator = new CubicInterpolator(
    DenseVector(Array(0.251, 0.315, 0.400, 0.446, 0.538, 0.653)),
    DenseVector(Array(0.251, 0.322, 0.387, 0.407, 0.412, 0.342))
  )

  def apply(x: Double) = interpolator(x)
}
