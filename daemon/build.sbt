// net.virtualvoid.sbt.graph.Plugin.graphSettings

name := "akka-daemon"

version := "0.1"

scalaVersion := "2.11.1"

javacOptions ++= Seq("-encoding", "UTF-8")

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "Java.net repository" at "http://download.java.net/maven/2/"

resolvers += "spray repo" at "http://repo.spray.io"

libraryDependencies += "commons-daemon" % "commons-daemon" % "1.0.15"

libraryDependencies ++= Seq(
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "com.typesafe.akka" %% "akka-actor"   % "2.3.5",
  "com.typesafe.akka" %% "akka-slf4j"   % "2.3.5",
  "com.typesafe.akka" %% "akka-remote"  % "2.3.5",
  "com.typesafe.akka" %% "akka-agent"   % "2.3.5",
  "com.typesafe.akka" %% "akka-testkit" % "2.3.5" % "test"
)

libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0"

libraryDependencies += "io.spray" %% "spray-can"  % "1.3.1"

libraryDependencies += "io.spray" %% "spray-http" % "1.3.1"

libraryDependencies += "io.spray" %% "spray-routing" % "1.3.1"

mainClass in (Compile,run) := Some("org.pimpay.daemon.example.Main")
