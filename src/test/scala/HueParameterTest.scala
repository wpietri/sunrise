import org.scalatest._
import org.scalatest.matchers.ShouldMatchers
import play.api.libs.json.Json


class HueParameterTest extends FlatSpec with ShouldMatchers {

  val api = new FakeApiConnector

  "A color" should "convert to JSON" in {
    val color = new Color(0.3, 0.4)
    color.toJsObject should be(Json.obj("xy" -> Json.arr(0.3, 0.4)))
  }

}
