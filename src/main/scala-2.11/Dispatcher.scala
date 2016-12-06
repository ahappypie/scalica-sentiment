import akka.actor.{Actor, PoisonPill, Props}

import scala.concurrent.duration._
/**
  * Created by Brian on 12/5/16.
  */
class Dispatcher extends Actor {

  import context.dispatcher

  val sqlActor = context.actorOf(Props[SQLActor])

  override def receive: Receive = {
    case d: FiniteDuration => dispatch(d)
    case p: PostBundle => context.actorOf(Props[SentimentActor]) ! p
    case s: SentimentBundle => sqlActor ! s
      sender ! PoisonPill
  }

  def dispatch(dur: FiniteDuration): Unit = {
    val scheduler = context.system.scheduler.schedule(0 seconds, dur) {
      sqlActor ! (System.currentTimeMillis() - dur.toMillis)
    }
  }
}

case class PostBundle(time: Long, tag: Int, posts: Array[String])
case class SentimentBundle(time: Long, tag: Int, sentiment: SENTIMENT_TYPE)