name := "QueryEngine"
organization := "com.example"

version := "0.1"
//version := "1.0-SNAPSHOT"

scalaVersion := "2.12.5"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.5"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"

val engineDependencies = Seq(
  "org.postgresql" % "postgresql" % "9.4.1208",
  "io.getquill" %% "quill-jdbc" % "2.4.2",
  "io.getquill" %% "quill-async-postgres" % "2.4.2"
)


lazy val queryEngine = (project in file("queryEngine")).
  settings(
    name := "queryEngine",
    version := "0.1",
    scalaVersion := "2.12.5",
    //routesGenerator := InjectedRoutesGenerator,
    //libraryDependencies += specs2 % Test,
    //resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases",
    libraryDependencies ++= engineDependencies
  )



//libraryDependencies += guice
//libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
val apiDependencies = "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test




lazy val api = (project in file("api"))
  .enablePlugins(PlayScala)
  .settings(
    name := "restApi",
    //organization := "com.example",
    version := "0.1",
    scalaVersion := "2.12.5",
    routesGenerator := InjectedRoutesGenerator,
    libraryDependencies += specs2 % Test,
    resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases",
    libraryDependencies += guice,
    libraryDependencies += apiDependencies
  )
  .dependsOn(queryEngine)

lazy val root = (project in file(".")).aggregate(api).dependsOn(api, queryEngine)
mainClass in Compile := (mainClass in Compile in api).value
