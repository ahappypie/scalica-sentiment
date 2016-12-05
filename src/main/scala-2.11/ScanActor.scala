import akka.actor.{Actor, Props}

/**
  * Created by Brian on 12/5/16.
  */
class ScanActor extends Actor {
  override def receive = {
    case t : Long => scanDB(t)
  }

  def scanDB(time : Long): Unit = {
    val posts = new Array[String](3)
    posts(0) = "I had a good day"
    posts(1) = "I had a very good day"
    posts(2) = "I had an awesome day"
    context.system.actorOf(Props[SentimentActor]) ! PostBundle(time, "goodday", posts)
  }
}
