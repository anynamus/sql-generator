ThisBuild / organization := "ca.servais"

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.8.1"

lazy val root =
  project
    .in(file("."))
    .settings(
      name := "sql-generator",

      scalacOptions ++= Seq(
        "-deprecation",
        "-feature",
        "-unchecked",
        "-Werror"
      ),

      libraryDependencies ++= Seq(
        "org.scalatest" %% "scalatest" % "3.2.19" % Test,
        "org.snakeyaml" % "snakeyaml-engine" % "2.10",
        "org.apache.commons" % "commons-csv" % "1.14.1"
      )
    )