import java.sql.Timestamp

import slick.driver.MySQLDriver.api._

/**
  * Created by Brian on 12/6/16.
  */
case class Micro_Sentiment(id:Option[Int], sentiment:String, date:Timestamp, tag:Int)

class SentimentTable(tag: Tag) extends Table[Micro_Sentiment](tag, "micro_sentiment") {
  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
  def sentiment_analysis = column[String]("sentiment_analysis")
  def sentiment_date = column[Timestamp]("sentiment_date")
  def hashtag_id = column[Int]("hashtag_id")
  def * = (id.?, sentiment_analysis, sentiment_date, hashtag_id) <> (Micro_Sentiment.tupled, Micro_Sentiment.unapply)
}
