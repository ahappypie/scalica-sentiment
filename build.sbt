name := "sentiment"

version := "1.0"

scalaVersion := "2.11.8"

logLevel := Level.Debug

lazy val akkaVersion = "2.4.14"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.slick" % "slick_2.11" % "3.1.1",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.1.0",
  "mysql" % "mysql-connector-java" % "5.1.40",
  "edu.stanford.nlp" % "stanford-corenlp" % "3.5.1",
  "edu.stanford.nlp" % "stanford-corenlp" % "3.5.1" classifier "models"
)