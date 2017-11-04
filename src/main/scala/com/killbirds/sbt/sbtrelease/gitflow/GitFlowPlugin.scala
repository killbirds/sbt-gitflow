package com.killbirds.sbt.sbtrelease.gitflow

import sbt._
import sbt.Keys._
import sbtrelease._

object GitFlowPlugin extends AutoPlugin {

  override def trigger = allRequirements
  override def requires = ReleasePlugin
}
