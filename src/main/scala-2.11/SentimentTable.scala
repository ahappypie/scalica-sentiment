import java.sql.Date

import slick.driver.MySQLDriver.api._

/**
  * Created by Brian on 12/6/16.
  */
case class Micro_Sentiment(id:Option[Int], sentiment:String, date:Date, tag:Int)

class SentimentTable(tag: Tag) extends Table[Micro_Sentiment](tag, "MICRO_SENTIMENT") {
  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
  def sentiment_analysis = column[String]("SENTIMENT_ANALYSIS")
  def sentiment_date = column[Date]("SENTIMENT_DATE")
  def hashtag_id = column[Int]("HASHTAG_ID")
  def * = (id.?, sentiment_analysis, sentiment_date, hashtag_id) <> (Micro_Sentiment.tupled, Micro_Sentiment.unapply)
}
