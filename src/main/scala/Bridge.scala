import java.net.URL

import com.stackmob.newman._
import com.stackmob.newman.dsl._
import play.api.libs.json._

import scala.concurrent._
import scala.concurrent.duration._


class Bridge {
  def light(number: Int) = new Light(this, number)

  val host: String = "philips-hue-bridge"
  val key: String = "080ed655b6f74144a29fd2f256eff3ae"
  implicit val httpClient = new ApacheHttpClient

  def get(path: String): JsValue = {
    val url = new URL("http", host, 80, "/api/" + key + path)
    val response = Await.result(GET(url).apply, 30.seconds)
    println(s"Response returned from $url with code ${response.code}, body ${response.bodyString}")
    val json: JsValue = Json.parse(response.bodyString)
    json
  }
}
