Global / organization := "dev.robsonpeixoto"
Global / version := "0.1.0-SNAPSHOT"
Global / scalaVersion := "2.12.17"
Global / scalacOptions ++= Seq("-language:postfixOps")
lazy val root = project
  .in(file("."))
  .settings(name := "Learn Scala 2")
