name := """sbt-gitflow"""
organization := "com.killbirds"

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

sbtPlugin := true

// ScalaTest
libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.11" % "test"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.11" % "test"

bintrayPackageLabels := Seq("sbt","plugin")
bintrayVcsUrl := Some("""git@github.com:killbirds/sbt-gitflow.git""")

addSbtPlugin("com.github.sbt" % "sbt-release" % "1.1.0")

console / initialCommands := """import com.killbirds.sbt.sbtrelease.gitflow._"""

