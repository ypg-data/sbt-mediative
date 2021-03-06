/*
 * Copyright 2017 Mediative
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mediative.sbt.oss

import scala.language.postfixOps
import sbt._
import sbt.Keys._
import bintray.BintrayPlugin.autoImport._
import MediativeProjectPlugin.autoImport._

/**
 * Settings for publishing a project to Bintray.
 *
 * To use add the following lines to the project definition:
 * {{{
 * .enablePlugins(MediativeBintrayPlugin)
 * }}}
 *
 * By default, it uses the [[MediativeProjectPlugin.autoImport.repoOrganization]]
 * setting as the Bintray organization (i.e. sbt-bintray's `bintrayOrganization`
 * setting and publishes to the Bintray organization's "maven" repository. To
 * publish to another repository use:
 * {{{
 * .settings(bintrayRepository := "sbt-plugins")
 * }}}
 *
 * This plugin must be enabled.
 */
object MediativeBintrayPlugin extends AutoPlugin {

  val BintrayMavenRepository = "maven"

  override def requires = plugins.JvmPlugin

  override def projectSettings: Seq[Setting[_]] = Seq(
    bintrayRepository := BintrayMavenRepository,
    bintrayOrganization := Some(repoOrganization.value),
    publishArtifact in Test := false,
    publishMavenStyle := bintrayRepository.value == BintrayMavenRepository
  )

}
