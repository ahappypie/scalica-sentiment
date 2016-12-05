import akka.actor.Actor
import akka.actor.Actor.Receive

/**
  * Created by Brian on 12/5/16.
  */
class WriteActor extends Actor {

  override def receive = {
    case SentimentBundle(time, tag, sentiment) =>
      println("write to db tag: " + tag + " sentiment: " + sentiment + " at time: " + time)
  }

}
