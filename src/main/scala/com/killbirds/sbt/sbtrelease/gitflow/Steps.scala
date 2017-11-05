package com.killbirds.sbt.sbtrelease.gitflow

import sbt._
import sbtrelease._
import ReleasePlugin.autoImport.ReleaseKeys.versions
import ReleasePlugin.autoImport.releaseVcs
import ReleasePlugin.autoImport.ReleaseStep

object Steps {
  private def releaseVersion(state: State): Either[String, String] = {
    state.get(versions).map(_._1).toRight {
      "Can not find a release version! This release step should be executed after inquireVersions."
    }
  }

  private def getGit(state: State): Either[String, Git] = {
    Project.extract(state).get(releaseVcs) match {
      case Some(git: Git) => Right(git)
      case _ =>
        Left("Aborting release. Working directory is not a repository of a recognized GIT.")
    }
  }

  private def run(fn: GitFlow => Either[String, Unit]): ReleaseStep = ReleaseStep({ state =>
    (for {
      git <- getGit(state)
      _ <- fn(GitFlow(git))
    } yield ()).swap.foreach(sys.error)
    state
  })

  private def run(fn: (GitFlow, String) => Either[String, Unit]): ReleaseStep = ReleaseStep({ state =>
    (for {
      git <- getGit(state)
      version <- releaseVersion(state)
      _ <- fn(GitFlow(git), version)
    } yield ()).swap.foreach(sys.error)
    state
  })

  val checkGitFlowExists: ReleaseStep = run { gitFlow =>
    for {
      _ <- gitFlow.exists()
      _ <- gitFlow.init()
    } yield ()
  }

  val gitFlowReleaseStart: ReleaseStep = run { (gitFlow, version) =>
    gitFlow.releaseStart(version)
  }

  val gitFlowReleaseFinish: ReleaseStep = run { (gitFlow, version) =>
    gitFlow.releaseFinish(version)
  }

  val pushMaster: ReleaseStep = run { gitFlow =>
    gitFlow.pushMaster()
  }
}
