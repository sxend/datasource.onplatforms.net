
organization := "net.onplatforms"

name := "datasource"

version := s"0.0.1-SNAPSHOT"

scalaVersion := "2.11.8"

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots"),
  Resolver.bintrayRepo("sxend", "releases"),
  Resolver.bintrayRepo("sxend", "snapshots"),
  "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"
)

val dependencies = {
  val slickVersion = "3.1.1"
  Seq(
    "com.typesafe.slick" %% "slick" % slickVersion,
    "com.typesafe.slick" %% "slick-codegen" % slickVersion,
    "joda-time" % "joda-time" % "2.7",
    "org.joda" % "joda-convert" % "1.7",
    "mysql" % "mysql-connector-java" % "6.0.5",
    "org.scalariform" %% "scalariform" % "0.1.8" % "compile"
  )
}

val RDB_HOST = Option(System.getProperty("sbt.RDB_HOST"))
  .orElse(Option(System.getenv("RDB_HOST"))).getOrElse("0.0.0.0")

val RDB_PORT = Option(System.getProperty("sbt.RDB_PORT"))
  .orElse(Option(System.getenv("RDB_PORT"))).getOrElse("3306")

val RDB_USER = Option(System.getProperty("sbt.RDB_USER"))
  .orElse(Option(System.getenv("RDB_USER"))).getOrElse("root")

val RDB_PASS = Option(System.getProperty("sbt.RDB_PASS"))
  .orElse(Option(System.getenv("RDB_PASS"))).getOrElse("")

val SCHEMA = Option(System.getProperty("sbt.SCHEMA")).getOrElse("")

val PACKAGE = Option(System.getProperty("sbt.PACKAGE")).getOrElse("")

lazy val slick = TaskKey[Seq[File]]("slick-gen")

lazy val datasource = Project(
  id="datasource",
  base=file("."),
  settings = Defaults.coreDefaultSettings ++ Seq(
    scalaVersion := "2.11.8",
    libraryDependencies ++= dependencies,
    slick := {
      val cp = (dependencyClasspath in Compile).value
      val r = (runner in Compile).value
      val slickDriver = "slick.driver.MySQLDriver"
      val jdbcDriver = "com.mysql.cj.jdbc.Driver"
      val url = s"jdbc:mysql://$RDB_HOST:$RDB_PORT/$SCHEMA?useSSL=false&nullNamePatternMatchesAll=true"
      val outputDir = new File("generated-src/main/scala").absolutePath
      val args = Array(slickDriver, jdbcDriver, url, outputDir, PACKAGE, RDB_USER, RDB_PASS)
      r.run("slick.codegen.SourceCodeGenerator", cp.files, args, Logger.Null)
      Seq(file(outputDir + "/Tables.scala"))
    },
    unmanagedSourceDirectories in Compile += baseDirectory.value / "generated-src/main/scala"
  )
)

publishMavenStyle := true

bintrayRepository := "releases"

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

javacOptions ++= Seq("-source", "1.8")

scalacOptions ++= Seq(
  "-feature",
  "-deprecation",
  "-unchecked",
  "-language:reflectiveCalls",
  "-language:postfixOps"
)