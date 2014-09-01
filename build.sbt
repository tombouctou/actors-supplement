name := "akka-examples"

version := "0.1"

scalaVersion := "2.11.1"

javacOptions ++= Seq("-encoding", "UTF-8")

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "Java.net repository" at "http://download.java.net/maven/2/"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor"   % "2.3.5",
  "com.typesafe.akka" %% "akka-slf4j"   % "2.3.5",
  "com.typesafe.akka" %% "akka-remote"  % "2.3.5",
  "com.typesafe.akka" %% "akka-agent"   % "2.3.5",
  "com.typesafe.akka" %% "akka-testkit" % "2.3.5" % "test"
)

mainClass in (Compile,run) := Some("org.pimpay.akka.examples.Main")
