import org.scalatest._

class LightTest extends FlatSpec with Matchers {

  "A light" should "know whether it's on" in {
    val light = new Bridge().light(1)
    //    light.on should be false
  }

}
