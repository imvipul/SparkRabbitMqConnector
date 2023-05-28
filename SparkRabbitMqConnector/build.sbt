ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "SparkRabbitMqConnector",
    idePackagePrefix := Some("com.vk.spark.rabbitmq.connector")
  )

resolvers ++= Seq(
  "mavenRepo" at "https://repository.apache.org/snapshots",
  "Flyway" at "https://flywaydb.org/repo"
)
libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "3.3.0",
  "org.apache.spark" %% "spark-sql" % "3.3.0",
  "org.apache.spark" %% "spark-mllib" % "3.3.0",
  "org.apache.spark" %% "spark-streaming" % "3.3.0",
  "org.twitter4j" % "twitter4j-core" % "4.0.4",
  "org.twitter4j" % "twitter4j-stream" % "4.0.4"
)

excludeDependencies ++= Seq(
  ExclusionRule("log4j", "log4j")
)
