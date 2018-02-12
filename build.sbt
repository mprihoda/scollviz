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
  .enablePlugins(ScalaJSBundlerPlugin)
  .settings(
    scalaJSUseMainModuleInitializer := true,
    scalacOptions += "-P:scalajs:sjsDefinedByDefault",
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "0.9.3",
      "io.suzaku" %%% "diode-core" % "1.1.3",
      "io.suzaku" %%% "diode-react" % "1.1.3",
      "com.github.japgolly.scalajs-react" %%% "core" % "1.1.1"
    ),
    npmDependencies in Compile ++= Seq("react" -> "15.6.1",
                                       "react-dom" -> "15.6.1"),
    npmDevDependencies in Compile ++= Seq("html-webpack-plugin" -> "2.30.1"),
    webpackBundlingMode := BundlingMode.LibraryOnly(),
    // Inspiration to implement --hot could be found at https://github.com/bphelan/scalajs-multiclient-example
    webpackDevServerExtraArgs in fastOptJS := Seq("--inline"),
    webpackConfigFile in fastOptJS := Some(
      baseDirectory.value / "webpack-fastopt.config.js")
  )
