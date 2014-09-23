import play.api.libs.json._


class Bridge(api: ApiConnector = new ApacheApiConnector("192.168.1.81")) {

  val key: String = "080ed655b6f74144a29fd2f256eff3ae"

  def light(number: Int) = new Light(this, number)

  def get(path: String): JsObject = api.get("/api/" + key + path)

  def put(path: String, data: JsObject) = api.put("/api/" + key + path, data)

}
