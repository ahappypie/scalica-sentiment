import akka.actor.{Actor, Props}

import scala.concurrent.duration._
/**
  * Created by Brian on 12/5/16.
  */
class Dispatcher extends Actor {

  import context.dispatcher

  val sqlActor = context.actorOf(Props[SQLActor])

  override def receive: Receive = {
    case t: Long => dispatch
    case p: PostBundle => context.actorOf(Props[SentimentActor]) ! p
    case s: SentimentBundle => sqlActor ! s
  }

  def dispatch(): Unit = {
    val scheduler = context.system.scheduler.schedule(0 seconds, 5 minutes) {
      sqlActor ! System.currentTimeMillis()
    }
  }
}

case class PostBundle(time: Long, tag: Int, posts: Array[String])
case class SentimentBundle(time: Long, tag: Int, sentiment: SENTIMENT_TYPE)