import akka.actor._
import akka.event.Logging
import hue._
import light._
import org.joda.time.{DateTimeZone, LocalTime}

import scala.concurrent.duration._


abstract class MyActor extends Actor {

  val log = Logging(context.system, this)


}


class MyMessage

class DaylightMode(bridge: Bridge) extends MyActor {

  override def receive: Actor.Receive = {
    case Tick =>
      val lightLevel = desiredLightOutput(LocalTime.now(Config.LocalTimeZone))
      log.info("desired level: {}", lightLevel)
      val lightStates = LightOutputCalculator(bridge.lights, lightLevel)
      log.debug("calculated states: {}", lightStates)
      for ((light, level) <- lightStates) {
        val parameters = paramsFor(light, level)
        log.info("setting light {} to {}", light, parameters)
        light.set(parameters: _*)
      }
  }

  def paramsFor(light: Light, output: LightOutput): Seq[HueParameter] = {
    if (output.lumens <= 0)
      Seq(On(false))
    else
      Seq(
        On(true),
        Color(output.x, output.y),
        Brightness((255 * output.lumens / light.maxLumens).toInt),
        TransitionTime(Config.UpdateFrequency))
  }

  def desiredLightOutput(t: LocalTime): LightOutput = {
    val desiredColor = ColorTemperatureCurve(DaylightColorCurve(t))
    val desiredLumens = DaylightLumensCurve(t)
    LightOutput(desiredColor._1, desiredColor._2, desiredLumens)
  }
}

class Wrangler extends MyActor {

  import context._

  val bridge = new Bridge("192.168.1.81", 80, "080ed655b6f74144a29fd2f256eff3ae")
  val allLights = bridge.group(0)
  val daylightMode = context.system.actorOf(DaylightMode.props(bridge), "daylight")


  override def receive: Receive = {
    case Start =>
      log.info("starting")
      context.system.scheduler.schedule(1.second, Config.UpdateFrequency, daylightMode, Tick)
      log.info("started")
  }
}


case object Start extends MyMessage

case object Tick extends MyMessage

object Daemon extends App {
  val system = ActorSystem("Sunrise")
  val wrangler = system.actorOf(Props[Wrangler], "wrangler")
  wrangler ! Start
}


object DaylightMode {
  def props(bridge: Bridge): Props = Props(new DaylightMode(bridge))
}

object Config {
  val UpdateFrequency = 15.second
  val LocalTimeZone = DateTimeZone.forID("America/Los_Angeles")

}



