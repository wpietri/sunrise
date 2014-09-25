import org.scalatest._
import org.scalatest.matchers.ShouldMatchers
import play.api.libs.json.Json

class LightTest extends FlatSpec with ShouldMatchers {

  def fixture = new {
    val api = new FakeApiConnector
    val light = new Bridge(api, "dummykey").light(1)
  }

  "A light" should "know whether it's on" in {
    val f = fixture

    f.api.nextGetResponse = Json.obj("state" -> Json.obj("on" -> false))
    f.light.on should be(false)

    f.api.nextGetResponse = Json.obj("state" -> Json.obj("on" -> true))
    f.light.on should be(true)

    f.api.lastPath should endWith("/lights/1")
  }

  it should "be color-settable" in {
    val f = fixture

    f.light.set(Color(0.65, 0.32))
    f.api.lastPath should endWith("/lights/1/state")
    f.api.lastData should be(Json.obj("xy" -> Json.arr(0.65, 0.32)))
  }

  it should "set multiple parameters" in {
    val f = fixture

    f.light.set(Color(0.65, 0.32), On(true), Brightness(127))
    f.api.lastPath should endWith("/lights/1/state")
    f.api.lastData should be(Json.obj("xy" -> Json.arr(0.65, 0.32), "on" -> true, "bri" -> 127))

  }

}
