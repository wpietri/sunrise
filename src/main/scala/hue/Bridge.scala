package hue

import play.api.libs.json._


class Bridge(api: ApiConnector, key: String) {


  def this(host: String, key: String) = this(new ApacheApiConnector(host, 80), key)

  def this(host: String, port: Int, key: String) = this(new ApacheApiConnector(host, port), key)


  def group(number: Int) = new Group(this, number)

  def light(number: Int) = new Light(this, number)

  def lights: Seq[Light] = get("/lights").keys.map(id => light(id.toInt)).toSeq

  def groups: Seq[Group] = get("/groups").keys.map(id => group(id.toInt)).toSeq


  def get(path: String): JsObject = api.get("/api/" + key + path)

  def put(path: String, data: JsObject) = api.put("/api/" + key + path, data)

}
