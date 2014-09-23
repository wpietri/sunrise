class Group(val bridge: Bridge, number: Int) extends HueDevice {

  override def path: String = s"/groups/$number/action"
}
