package hue

import org.scalatest._
import org.scalatest.matchers.ShouldMatchers
import play.api.libs.json.{JsObject, Json}

class GroupTest extends FlatSpec with ShouldMatchers {

  def fixture = new {
    val api = new FakeApiConnector
    val bridge = new Bridge(api, "dummykey")
  }

  it should "know its lights" in {
    val f = fixture
    val group = f.bridge.group(1)
    f.api.addResponse("/groups/1", SingleGroupResponse.as[JsObject])

    print(f.api)
    val lights = group.lights
    f.api.lastPath should endWith("/groups/1")
    lights.size should be(2)

  }

  private val SingleGroupResponse = Json.parse(
    """
          {
          	"action": {
          		"on": true,
          		"hue": 0,
          		"effect": "none",
          		"bri": 100,
          		"sat": 100,
          		"ct": 500,
          		"xy": [0.5, 0.5]
          	},
          	"lights": [
          		"1",
          		"2"
          	],
                  "state":{"any_on":true, "all_on":true},
                     "type":"Room",   "class":"Bedroom",   "name":"Master bedroom" }
    """.stripMargin)


}
