package app

import akka.actor._
import akka.event.Logging
import hue._
import light._
import org.joda.time.LocalTime

import scala.concurrent.duration._


class MyMessage

case object Start extends MyMessage

case object Tick extends MyMessage


object Daemon extends App {
  val system = ActorSystem("Sunrise")
  val wrangler = system.actorOf(Props[Wrangler], "wrangler")
  wrangler ! Start
}

abstract class MyActor extends Actor {
  val log = Logging(context.system, this)
}

class Wrangler extends MyActor {

  import context._

  val bridge = new Bridge(Settings.bridgeAddress, Settings.bridgePort, Settings.bridgeKey)
  val allLights = bridge.group(0)
  val daylightMode = context.system.actorOf(DefaultMode.props(bridge), "daylight")

  override def receive: Receive = {
    case Start =>
      log.info("starting")
      context.system.scheduler.schedule(1.second, Settings.updateFrequency, daylightMode, Tick)
      log.info("started")
  }
}

class DefaultMode(bridge: Bridge) extends MyActor {
  val defaultProgram = new DefaultLightProgram(Settings.dawnStart, Settings.dawnLength, Settings.duskStart, Settings.duskLength)

  override def receive: Actor.Receive = {
    case Tick =>
      val lightLevel = defaultProgram(LocalTime.now(Settings.localTimeZone))
      log.info("desired level: {}", lightLevel)
      val lightStates = LightOutputCalculator(bridge.lights, lightLevel)
      log.debug("calculated states: {}", lightStates)
      for ((light, level) <- lightStates) {
        log.info("setting light {} to {}", light, level)
        light.set(level, Settings.updateFrequency)
      }
  }

}

object DefaultMode {
  def props(bridge: Bridge): Props = Props(new DefaultMode(bridge))
}

