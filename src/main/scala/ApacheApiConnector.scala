import java.net.URL

import com.stackmob.newman.ApacheHttpClient
import com.stackmob.newman.dsl._
import play.api.libs.json.{JsObject, Json}

import scala.concurrent.Await
import scala.concurrent.duration._

class ApacheApiConnector(host: String) extends ApiConnector {
  implicit val httpClient: ApacheHttpClient = new ApacheHttpClient()

  override def get(path: String): JsObject = {
    val url = new URL("http", host, 80, path)
    val response = Await.result(GET(url).apply, 30.seconds)
    println(s"Response returned from $url with code ${response.code}, body ${response.bodyString}")

    Json.parse(response.bodyString) match {
      case j: JsObject => j
      case _ => throw new ClassCastException
    }
  }

  override def put(path: String, data: JsObject): JsObject = ???
}
