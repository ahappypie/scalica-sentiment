import java.sql.Timestamp

import akka.actor.{Actor, ActorRef, Props}
import slick.driver.MySQLDriver.api._
import slick.jdbc.GetResult

import scala.collection.mutable.{ListBuffer, Map}

/**
  * Created by Brian on 12/5/16.
  */
class SQLActor extends Actor {

  import context.dispatcher

  val db = Database.forConfig("mysqldb")

  override def receive = {
    case t : Long => scanDB(t, sender)
    case s : SentimentBundle => write(s)
  }

  def scanDB(time : Long, sender: ActorRef): Unit = {
    val scan: Map[Int, ListBuffer[String]] = Map()
    val query = scanQuery(new Timestamp(time).toString)
    db.run(query).onSuccess {
      case s => println(s.size)
        for(result <- s) {
        val posts: Option[ListBuffer[String]] = scan.get(result.tag)
        if(posts.isDefined) {
          posts.get += result.text
        }
        else {
          scan.put(result.tag, new ListBuffer[String] += result.text)
        }
      }
      for(tag <- scan.keys) {
        sender ! PostBundle(System.currentTimeMillis(), tag, scan(tag))
      }
    }
  }

  implicit val getScanResult = GetResult(r => ScanResult(r.<<, r.<<))
  def scanQuery(filterTime: String): DBIO[Seq[ScanResult]] = sql"""
      SELECT p.text, t.id
      FROM micro_posttag AS pt
      INNER JOIN micro_post AS p ON p.id = pt.post_id
      INNER JOIN micro_hashtag AS t ON t.id = pt.hashtag_id
      WHERE p.pub_date > ${filterTime}""".as[ScanResult]

  val sentiment = TableQuery[SentimentTable]

  def write(s: SentimentBundle): Unit = {
    println("writing to db")
    db.run(sentiment += Micro_Sentiment(None, s.sentiment.toString, new Timestamp(s.time), s.tag)).onFailure {
      case f => println(f)
    }
  }
}

case class ScanResult(text: String, tag: Int)