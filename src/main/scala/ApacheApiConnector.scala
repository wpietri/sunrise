import java.net.URL
import java.util.concurrent.{Executors, ThreadFactory}

import com.stackmob.newman.ApacheHttpClient
import com.stackmob.newman.dsl._
import com.stackmob.newman.response.HttpResponse
import play.api.libs.json.{JsArray, JsObject, Json}

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext, ExecutionContextExecutorService}

class ApacheApiConnector(host: String, port: Int = 80) extends ApiConnector {
  implicit val httpClient: ApacheHttpClient = newApacheHttpClient

  def newApacheHttpClient: ApacheHttpClient = {
    implicit val ctx = createUglyWorkaround
    new ApacheHttpClient()
  }

  def createUglyWorkaround: ExecutionContextExecutorService = {
    val factory = new ThreadFactory {
      override def newThread(run: Runnable) = {
        val t = new Thread(run)
        t.setDaemon(true)
        t
      }
    }
    val svc = Executors.newFixedThreadPool(20, factory) //or choose a cached thread pool, etc...
    val x = ExecutionContext.fromExecutorService(svc)
    x
  }

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
    val url = new URL("http", host, port, path)
    url
  }


}
