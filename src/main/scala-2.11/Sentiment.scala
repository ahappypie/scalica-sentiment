import akka.actor.{ActorSystem, Props}
import scala.concurrent.duration._
/**
  * Created by Brian on 12/5/16.
  */
object Sentiment {

  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem()
    implicit val executionContext = system.dispatcher

    val scheduler = system.scheduler.schedule(0 seconds, 5 minutes) {
      system.actorOf(Props[ScanActor]) ! System.nanoTime()
    }
  }

}

case class PostBundle(time: Long, tag: String, posts: Array[String])
case class SentimentBundle(time: Long, tag: String, sentiment: SENTIMENT_TYPE)