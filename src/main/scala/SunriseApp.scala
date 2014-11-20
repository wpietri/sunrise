import hue._

import scala.concurrent.duration._


object SunriseApp extends App {

  val pace = if (args.length > 0) args(0).toInt else 60
  println(s"pace is $pace")

  val bridge = new Bridge("192.168.1.81", 80, "080ed655b6f74144a29fd2f256eff3ae")
  //  val bridge = new Bridge("localhost", 2233, "080ed655b6f74144a29fd2f256eff3ae")
  val l1 = bridge.light(1)
  val l2 = bridge.light(2)
  val l3 = bridge.light(3)
  val strip = bridge.light(4)
  val allLights = bridge.group(0)

  val on = On(true)
  val rightAway = TransitionTime(0.seconds)

  val red = Color(0.65, 0.32)
  val redOrange = Color(0.62, 0.35)

  val dimmest = Brightness(0)

  val fiveMinutes = pace * 5.seconds
  val tenMinutes = pace * 10.seconds

  // sunrise comes up over 30 minutes to full brightness

  // 5 minutes of lightstrip red
  strip.set(dimmest, red, on, rightAway)
  pause()
  strip.set(Brightness(255), TransitionTime(fiveMinutes))
  wait(fiveMinutes)


  // 5 minutes: bring light 1 up
  l1.set(dimmest, red, on, rightAway)
  pause()
  l1.set(Brightness(50), redOrange, TransitionTime(fiveMinutes))
  wait(fiveMinutes)

  // 5 minutes: bring light 2&3 up; shift light 1 color
  List(l2, l3).foreach(_.set(dimmest, redOrange, on, rightAway))
  pause()
  List(l1, l2, l3).foreach(_.set(Brightness(50), Color(0.58, 0.39), TransitionTime(fiveMinutes)))
  wait(fiveMinutes)

  // now lights are in sync, so use groups

  // 10 minutes: shift to warm light at medium brightness
  allLights.set(Brightness(125), Color(0.5261, 0.4132), TransitionTime(tenMinutes))
  wait(tenMinutes)

  //    # 10 minutes: shift to normal light at high brightness
  allLights.set(Brightness(255), Color(0.4358, 0.4037), TransitionTime(tenMinutes))
  wait(tenMinutes)

  // shift to daytime color over a long while
  allLights.set(Color(0.3124, 0.3226), TransitionTime(4.hours))

  def pause() {
    wait(0.1.seconds)
  }

  def wait(time: FiniteDuration) {
    Thread.sleep(time.toMillis)
  }
}
