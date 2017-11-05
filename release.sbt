
// Release
import ReleaseTransformations._
import com.killbirds.sbt.sbtrelease.gitflow.Steps._

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  checkGitFlowExists,                       //git-flow
  inquireVersions,
  runClean,
  runTest,
  gitFlowReleaseStart,                      //git-flow
  setReleaseVersion,
  commitReleaseVersion,                     //performs the initial git checks
  gitFlowReleaseFinish,                     //git-flow
  pushMaster,                               //git-flow
  setNextVersion,
  commitNextVersion,
  pushChanges                               //checks that an upstream branch is properly configured
)

