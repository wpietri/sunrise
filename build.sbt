name := "sunrise"

version := "1.0"

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

//libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.2.1" % "test"

libraryDependencies += "org.scalamock" %% "scalamock-scalatest-support" % "3.0.1" % "test"

libraryDependencies += "com.stackmob" %% "newman" % "1.3.5"

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.2.1"
