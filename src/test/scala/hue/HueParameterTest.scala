package hue

import org.scalatest._
import org.scalatest.matchers.ShouldMatchers
import play.api.libs.json.Json

import scala.concurrent.duration._


class HueParameterTest extends FlatSpec with ShouldMatchers {

  val api = new FakeApiConnector

  "A color" should "convert to JSON" in {
    Color(0.3, 0.4).toJsObject should be(Json.obj("xy" -> Json.arr(0.3, 0.4)))
  }

  "On" should "convert to JSON" in {
    On(true).toJsObject should be(Json.obj("on" -> true))
  }

  "Brightness" should "convert to JSON" in {
    Brightness(128).toJsObject should be(Json.obj("bri" -> 128))
  }

  it should "enforce bounds" in {
    intercept[Exception] {
      Brightness(-1)
    }
    intercept[Exception] {
      Brightness(256)
    }
  }

  "TransitionTime" should "convert to JSON" in {
    TransitionTime(30.seconds).toJsObject should be(Json.obj("transitiontime" -> 300))
  }

  it should "handle too-long times gracefully" in {
    TransitionTime(30.days).toJsObject should be(Json.obj("transitiontime" -> 65535))

  }


}
