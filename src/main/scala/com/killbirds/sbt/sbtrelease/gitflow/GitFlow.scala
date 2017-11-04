package com.killbirds.sbt.sbtrelease.gitflow

import scala.sys.process._
import scala.util.Try

import sbt._
import sbt.Keys._
import sbtrelease._

class GitFlow(git: Git) {
  private def baseDir = git.baseDir

  private def cmd(args: Any*): ProcessBuilder = git.cmd(("flow" +: args):_ *)

  private def run(error: String)(process: ProcessBuilder): Either[String, Unit] = {
    if (process.! == 0) {
      Right(())
    } else {
      Left(error)
    }
  }

  def exists() = run("git-flow is required. See https://github.com/nvie/gitflow.") {
    cmd("version")
  }

  def init() = run("Failed to run 'git flow init -d'.") {
    cmd("init", "-d")
  }

  def releaseStart(version: String) = run(s"Failed to run 'git flow release start $version") {
    cmd("release", "start", version)
  }

  def releaseFinish(version: String) = run(s"Failed to run 'git flow release finish $version") {
    (s"echo 'Releasing $version.'" #> new File(baseDir, ".git/MY_TAGMSG")) #&&
    git.cmd("config", "core.editor", "mv .git/MY_TAGMSG") #&&
    cmd("release", "finish", version) #&&
    git.cmd("config", "--unset", "core.editor")
  }

  def pushMaster() = run(s"Failed to run 'git push origin master'") {
    git.cmd("push", "origin", "master")
  }
}

object GitFlow {
  def apply(git: Git): GitFlow = new GitFlow(git)
}

