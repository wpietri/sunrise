import sbtassembly.Plugin.AssemblyKeys._

// put this at the top of the file

// name := "sunrise"

version := "1.0"

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "org.scalanlp" %% "breeze" % "0.9",
  "org.scalanlp" %% "breeze-natives" % "0.9"
)

resolvers ++= Seq(
  "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases/"
)

//libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.2.1" % "test"

libraryDependencies += "org.scalamock" %% "scalamock-scalatest-support" % "3.0.1" % "test"

libraryDependencies += "com.stackmob" %% "newman" % "1.3.5"

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.2.1"

libraryDependencies += "com.github.nscala-time" %% "nscala-time" % "1.4.0"

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.6"

mainClass := Some("SunriseApp")

exportJars := true


assemblySettings

jarName in assembly := "sunrise.jar"