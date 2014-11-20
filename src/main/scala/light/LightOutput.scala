package light

case class LightOutput(x: Double, y: Double, lumens: Double) {

  def withLumens(newLumens: Double) = LightOutput(this.x, this.y, newLumens)


  /** Adds LightOutputs. For now, colors have to be the same. */
  def +(other: LightOutput): LightOutput = {
    assert(this.x == other.x)
    assert(this.y == other.y)
    LightOutput(this.x, this.y, this.lumens + other.lumens)
  }

  /** Subtracts LightOutputs. For now, colors have to be the same. */
  def -(other: LightOutput): LightOutput = {
    assert(this.x == other.x)
    assert(this.y == other.y)
    LightOutput(this.x, this.y, this.lumens - other.lumens)
  }


  override def toString: String = f"LightOutput($x%1.3f,$y%1.3f,$lumens%1.0f)"
}
