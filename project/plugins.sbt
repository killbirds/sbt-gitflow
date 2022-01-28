//libraryDependencies += "org.scala-sbt" %% "scripted-plugin" % sbtVersion.value

addSbtPlugin("org.foundweekends" % "sbt-bintray" % "0.6.1")

// This project is its own plugin :)
Compile / unmanagedSourceDirectories += baseDirectory.value.getParentFile / "src" / "main" / "scala"

addSbtPlugin("com.github.sbt" % "sbt-release" % "1.1.0")