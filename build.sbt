name := "scala_coll"

version := "0.1"

scalaVersion := "2.12.4"

lazy val commonSettings = Seq(
  organization := "net.prihoda.scala.coll",
  scalacOptions := Seq(
    "-unchecked",
    "-feature",
    "-deprecation",
    "-encoding",
    "utf8",
    // "-Xfatal-warnings",
    "-Xlint",
    "-Ywarn-adapted-args",
    "-Ywarn-dead-code",
    "-Ywarn-numeric-widen",
    "-Ywarn-value-discard",
    "-Xfuture",
    "-Ywarn-unused-import"
  ),
  logBuffered in Test := false,
  scalacOptions in (Compile, console) ~= (_.filterNot(
    Set(
      "-Ywarn-unused-import",
      "-Xfatal-warnings"
    )
  ))
)

lazy val root = (project in file(".")).settings(commonSettings)

lazy val web = project
  .settings(commonSettings)
  .enablePlugins(ScalaJSPlugin, WorkbenchPlugin)
  .settings(
    scalaJSUseMainModuleInitializer := true,
    scalacOptions += "-P:scalajs:sjsDefinedByDefault",
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "0.9.3",
      "com.lihaoyi" %%% "scalatags" % "0.6.7",
      "io.suzaku" %%% "diode-core" % "1.1.3"
    ),
    workbenchDefaultRootObject := Some(
      ("web/target/scala-2.12/classes/index.html", "web/target/scala-2.12"))
  )
