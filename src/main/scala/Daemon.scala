import akka.actor._
import akka.event.Logging
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

class DaylightMode extends MyActor {
  override def receive: Actor.Receive = {
    case Tick =>
      val t = LocalTime.now()
      val desiredLumens = DaylightLumensCurve(t)
      val desiredColor = ColorTemperatureCurve(DaylightColorCurve(t))
      log.info("{} {} {}", desiredLumens, desiredColor._1, desiredColor._2)
  }
}


class Wrangler extends MyActor {

  import context._

  val bridge = new Bridge("192.168.1.81", 80, "080ed655b6f74144a29fd2f256eff3ae")
  val allLights = bridge.group(0)
  val daylightMode = context.system.actorOf(Props[DaylightMode], "daylight")


  override def receive: Receive = {
    case Start =>
      log.info("starting")
      context.system.scheduler.schedule(1.second, 1.second, daylightMode, Tick)
      log.info("started")
  }
}

