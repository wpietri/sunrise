package hue

import org.scalatest._
import org.scalatest.matchers.ShouldMatchers
import play.api.libs.json.{JsObject, Json}


class BridgeTest extends FlatSpec with ShouldMatchers {

  def fixture = new {
    val api = new FakeApiConnector
    val bridge = new Bridge(api, "dummykey")
  }

  "A bridge" should "know all its lights" in {
    val f = fixture

    f.api.nextGetResponse = FullBridgeLightResponse.as[JsObject]
    val lights = f.bridge.lights
    f.api.lastPath should endWith("/lights")
    lights.size should be(5)
  }

  it should "know all groups" in {
    val f = fixture

    f.api.nextGetResponse = AllGroupsResponse.as[JsObject]
    val groups = f.bridge.groups
    f.api.lastPath should endWith("/groups")
    groups.size should be(2)
  }

  private val FullBridgeLightResponse = Json.parse(
    """
        {
        	"1": {
        		"state": {
        			"on": false,
        			"bri": 254,
        			"hue": 34530,
        			"sat": 240,
        			"xy": [
        				0.3125,
        				0.3227
        			],
        			"ct": 153,
        			"alert": "none",
        			"effect": "none",
        			"colormode": "xy",
        			"reachable": true
        		},
        		"type": "Extended color light",
        		"name": "Bed 1",
        		"modelid": "LCT001",
        		"swversion": "66009663",
        		"pointsymbol": {
        			"1": "none",
        			"2": "none",
        			"3": "none",
        			"4": "none",
        			"5": "none",
        			"6": "none",
        			"7": "none",
        			"8": "none"
        		}
        	},
        	"2": {
        		"state": {
        			"on": false,
        			"bri": 254,
        			"hue": 34530,
        			"sat": 240,
        			"xy": [
        				0.3125,
        				0.3227
        			],
        			"ct": 153,
        			"alert": "none",
        			"effect": "none",
        			"colormode": "xy",
        			"reachable": true
        		},
        		"type": "Extended color light",
        		"name": "Bed 2",
        		"modelid": "LCT001",
        		"swversion": "66009663",
        		"pointsymbol": {
        			"1": "none",
        			"2": "none",
        			"3": "none",
        			"4": "none",
        			"5": "none",
        			"6": "none",
        			"7": "none",
        			"8": "none"
        		}
        	},
        	"3": {
        		"state": {
        			"on": false,
        			"bri": 254,
        			"hue": 34530,
        			"sat": 240,
        			"xy": [
        				0.3125,
        				0.3227
        			],
        			"ct": 153,
        			"alert": "none",
        			"effect": "none",
        			"colormode": "xy",
        			"reachable": true
        		},
        		"type": "Extended color light",
        		"name": "Bed 3",
        		"modelid": "LCT001",
        		"swversion": "66009663",
        		"pointsymbol": {
        			"1": "none",
        			"2": "none",
        			"3": "none",
        			"4": "none",
        			"5": "none",
        			"6": "none",
        			"7": "none",
        			"8": "none"
        		}
        	},
        	"4": {
        		"state": {
        			"on": true,
        			"bri": 254,
        			"hue": 40253,
        			"sat": 111,
        			"xy": [
        				0.3108,
        				0.3227
        			],
        			"alert": "none",
        			"effect": "none",
        			"colormode": "xy",
        			"reachable": true
        		},
        		"type": "Color light",
        		"name": "LightStrips 1",
        		"modelid": "LST001",
        		"swversion": "66010400",
        		"pointsymbol": {
        			"1": "none",
        			"2": "none",
        			"3": "none",
        			"4": "none",
        			"5": "none",
        			"6": "none",
        			"7": "none",
        			"8": "none"
        		}
        	},
        	"5": {
        		"state": {
        			"on": false,
        			"bri": 24,
        			"alert": "none",
        			"effect": "none",
        			"reachable": true
        		},
        		"type": "Dimmable light",
        		"name": "Lux Lamp 1",
        		"modelid": "LWB004",
        		"swversion": "66012040",
        		"pointsymbol": {
        			"1": "none",
        			"2": "none",
        			"3": "none",
        			"4": "none",
        			"5": "none",
        			"6": "none",
        			"7": "none",
        			"8": "none"
        		}
        	}
        }
    """.stripMargin)

  private val AllGroupsResponse = Json.parse(
    """
            {
              "1": {
                  "name": "Group 1",
                  "lights": [
                      "1",
                      "2"
                  ],
                  "type": "LightGroup",
                  "action": {
                      "on": true,
                      "bri": 254,
                      "hue": 10000,
                      "sat": 254,
                      "effect": "none",
                      "xy": [
                          0.5,
                          0.5
                      ],
                      "ct": 250,
                      "alert": "select",
                      "colormode": "ct"
                  }
              },
              "2": {
                  "name": "Group 2",
                  "lights": [
                      "3",
                      "4",
                      "5"
                  ],
                  "type": "LightGroup",
                  "action": {
                      "on": true,
                      "bri": 153,
                      "hue": 4345,
                      "sat": 254,
                      "effect": "none",
                      "xy": [
                          0.5,
                          0.5
                      ],
                      "ct": 250,
                      "alert": "select",
                      "colormode": "ct"
                  }
              }
          }
    """.stripMargin)

}
