import akka.actor.{ActorSystem, Props}
import scala.concurrent.duration._
/**
  * Created by Brian on 12/5/16.
  */
object Sentiment {

  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem()
    implicit val executionContext = system.dispatcher

    system.actorOf(Props[Dispatcher]) ! System.nanoTime()
  }

}