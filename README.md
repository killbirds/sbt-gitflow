# git-flow

git-flow for sbt-release

## Usage

This plugin requires sbt 1.0.0+

```
// build.sbt
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
  commitReleaseVersion,
  gitFlowReleaseFinish,                     //git-flow
  pushMaster,                               //git-flow
  setNextVersion,
  commitNextVersion,
  pushChanges
)
```

### Testing

[killbirds/sbt-gitflow-test](https://github.com/killbirds/sbt-gitflow-test)

Run `sbt "release with-defaults"`


### Reference

https://github.com/ServiceRocket/sbt-git-flow

