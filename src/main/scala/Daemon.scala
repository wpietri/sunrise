import akka.actor._
import akka.event.Logging
import hue._
import light._
import org.joda.time.LocalTime

import scala.concurrent.duration._


class MyMessage

case object Start extends MyMessage

case object Tick extends MyMessage


abstract class MyActor extends Actor {
  val log = Logging(context.system, this)
}

object Daemon extends App {
  val system = ActorSystem("Sunrise")
  val wrangler = system.actorOf(Props[Wrangler], "wrangler")
  wrangler ! Start
}

class Wrangler extends MyActor {

  import context._

  val bridge = new Bridge(Settings.bridgeAddress, Settings.bridgePort, Settings.bridgeKey)
  val allLights = bridge.group(0)
  val daylightMode = context.system.actorOf(DaylightMode.props(bridge), "daylight")

  override def receive: Receive = {
    case Start =>
      log.info("starting")
      context.system.scheduler.schedule(1.second, Settings.updateFrequency, daylightMode, Tick)
      log.info("started")
  }
}


class DaylightMode(bridge: Bridge) extends MyActor {

  override def receive: Actor.Receive = {
    case Tick =>
      val lightLevel = desiredLightOutput(LocalTime.now(Settings.localTimeZone))
      log.info("desired level: {}", lightLevel)
      val lightStates = LightOutputCalculator(bridge.lights, lightLevel)
      log.debug("calculated states: {}", lightStates)
      for ((light, level) <- lightStates) {
        log.info("setting light {} to {}", light, level)
        light.set(level, Settings.updateFrequency)
      }
  }

  // todo: this moves to the new calculator class (which needs a better name than that)
  def desiredLightOutput(t: LocalTime): LightOutput = {
    val desiredColor = ColorTemperatureCurve(DaylightColorCurve(t))
    val desiredLumens = DaylightLumensCurve(t)
    LightOutput(desiredColor._1, desiredColor._2, desiredLumens)
  }
}

object DaylightMode {
  def props(bridge: Bridge): Props = Props(new DaylightMode(bridge))
}

