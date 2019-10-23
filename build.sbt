name := """sbt-gitflow"""
organization := "com.killbirds"

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

sbtPlugin := true

// ScalaTest
libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.8" % "test"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % "test"

bintrayPackageLabels := Seq("sbt","plugin")
bintrayVcsUrl := Some("""git@github.com:killbirds/sbt-gitflow.git""")

addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.12")

initialCommands in console := """import com.killbirds.sbt.sbtrelease.gitflow._"""

