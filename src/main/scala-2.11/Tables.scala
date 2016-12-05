import java.sql.Date

import slick.driver.MySQLDriver.api._

/**
  * Created by Brian on 12/5/16.
  */
class Post(tag: Tag) extends Table[(Int, Int, String, Date)](tag, "POST") {
  def id = column[Int]("ID", O.PrimaryKey)
  def user = column[Int]("USER")
  def text = column[String]("TEXT")
  def date = column[Date]("date_posted")
  def * = (id, user, text, date)
}

class Hashtag(tag: Tag) extends Table[(Int, String)](tag, "HASHTAG") {
  def id = column[Int]("ID", O.PrimaryKey)
  def text = column[String]("TEXT")
  def * = (id, text)
}