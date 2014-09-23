import java.net.URL

import com.stackmob.newman.ApacheHttpClient
import com.stackmob.newman.dsl._
import com.stackmob.newman.response.HttpResponse
import play.api.libs.json.{JsArray, JsObject, Json}

import scala.concurrent.Await
import scala.concurrent.duration._

class ApacheApiConnector(host: String) extends ApiConnector {
  implicit val httpClient: ApacheHttpClient = new ApacheHttpClient()

  override def get(path: String): JsObject = {
    val request = GET(url(path))
    val response = Await.result(request.apply, 30.seconds)
    noteResponse(path, response)
    Json.parse(response.bodyString) match {
      case o: JsObject => o
      case _ => throw new ClassCastException
    }
  }

  override def put(path: String, data: JsObject): JsArray = {
    val request = PUT(url(path)).addBody(Json.prettyPrint(data))
    println(s"Sending request to $path with data $data")
    val response = Await.result(request.apply, 30.seconds)
    noteResponse(path, response)
    Json.parse(response.bodyString) match {
      case a: JsArray => a
      case _ => throw new ClassCastException
    }

  }


  def noteResponse(path: String, response: HttpResponse) {
    println(s"Response returned from $path with code ${response.code}, body ${response.bodyString}")
  }

  def url(path: String): URL = {
    val url = new URL("http", host, 80, path)
    url
  }


}
