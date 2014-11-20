package hue

import org.scalatest._
import org.scalatest.matchers.ShouldMatchers
import play.api.libs.json.Json

class GroupTest extends FlatSpec with ShouldMatchers {

  val api = new FakeApiConnector

  "A group" should "set things" in {
    val group = new Bridge(api, "dummykey").group(0)
    group.set(On(true), Color(0.3, 0.4))
    api.lastPath should endWith("/groups/0/action")
    api.lastData should be(Json.obj("on" -> true, "xy" -> Json.arr(0.3, 0.4)))

  }


}
