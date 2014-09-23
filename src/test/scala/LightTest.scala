import org.scalatest._
import org.scalatest.matchers.ShouldMatchers
import play.api.libs.json.Json

class LightTest extends FlatSpec with ShouldMatchers {

  val api = new FakeApiConnector

  "A light" should "know whether it's on" in {

    val light = new Bridge(api).light(1)

    api.nextGetResponse = Json.obj("state" -> Json.obj("on" -> false))
    light.on should be(false)

    api.nextGetResponse = Json.obj("state" -> Json.obj("on" -> true))
    light.on should be(true)

    api.lastPath should endWith("/lights/1")
  }

  it should "be color-settable" in {

    val light = new Bridge(api).light(1)
    light.set(Color(0.65, 0.32))
    api.lastPath should endWith("/lights/1/state")
    api.lastData should be(Json.obj("xy" -> Json.arr(0.65, 0.32)))
  }

  it should "set multiple parameters" in {
    val light = new Bridge(api).light(1)
    light.set(Color(0.65, 0.32), On(true), Brightness(127))
    api.lastPath should endWith("/lights/1/state")
    api.lastData should be(Json.obj("xy" -> Json.arr(0.65, 0.32), "on" -> true, "bri" -> 127))

  }

}
