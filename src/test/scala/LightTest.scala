import org.scalatest._
import org.scalatest.matchers.ShouldMatchers
import play.api.libs.json.Json

class LightTest extends FlatSpec with ShouldMatchers {

  val api = new FakeApiConnector

  "A light" should "know whether it's on" in {

    val light = new Bridge(api).light(1)

    api.nextResponse = Json.obj("state" -> Json.obj("on" -> false))
    light.on should be(false)

    api.nextResponse = Json.obj("state" -> Json.obj("on" -> true))
    light.on should be(true)

    api.lastPath should be("/lights/1")
  }

}
