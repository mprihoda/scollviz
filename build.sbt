name := "scala_coll"

version := "0.1"

lazy val reactJS = Seq(
  "react" -> "^16.2.0",
  "react-dom" -> "^16.2.0",
)

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

lazy val macroAnnotationSettings = Seq(
  addCompilerPlugin("org.scalameta" % "paradise" % "3.0.0-M11" cross CrossVersion.full),
  scalacOptions += "-Xplugin-require:macroparadise",
  scalacOptions in (Compile, console) ~= (_ filterNot (_ contains "paradise")) // macroparadise plugin doesn't work in repl yet.
)

lazy val root = (project in file(".")).settings(commonSettings)

lazy val `semantic-ui-facade` =
  project
    .settings(commonSettings)
    .enablePlugins(ScalaJSBundlerPlugin)
    .settings(
      scalacOptions += "-P:scalajs:sjsDefinedByDefault",
      useYarn := true,
      libraryDependencies ++= Seq(
        "me.shadaj" %%% "slinky-web" % "0.3.0"
      ),
      npmDependencies in Compile ++= reactJS ++ Seq(
        "semantic-ui-less" -> "^2.2.12",
        "semantic-ui-react" -> "^0.77.0"
      ),
      webpackBundlingMode := BundlingMode.LibraryOnly(),
      macroAnnotationSettings
    )

lazy val web = project
  .settings(commonSettings)
  .enablePlugins(ScalaJSBundlerPlugin)
  .settings(
    scalacOptions += "-P:scalajs:sjsDefinedByDefault",
    useYarn := true,
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "0.9.3",
      "io.suzaku" %%% "diode-core" % "1.1.3",
      "io.suzaku" %%% "diode-react" % "1.1.3",
      "me.shadaj" %%% "slinky-web" % "0.3.0",
      "me.shadaj" %%% "slinky-hot" % "0.3.0",
      "me.shadaj" %%% "slinky-scalajsreact-interop" % "0.3.0"
    ),
    npmDependencies in Compile ++= reactJS ++ Seq("react-proxy" -> "^1.1.8"),
    npmDevDependencies in Compile ++= Seq("html-webpack-plugin" -> "^2.30.1",
                                          "file-loader" -> "^1.1.5",
                                          "style-loader" -> "^0.19.0",
                                          "css-loader" -> "^0.28.7",
                                          "copy-webpack-plugin" -> "^4.2.0"),
    webpackBundlingMode := BundlingMode.LibraryOnly(),
    // Inspiration to implement --hot could be found at https://github.com/bphelan/scalajs-multiclient-example
    webpackDevServerExtraArgs in fastOptJS := Seq("--inline", "--hot"),
    webpackConfigFile in fastOptJS := Some(
      baseDirectory.value / "webpack-fastopt.config.js"),
    webpackConfigFile in fullOptJS := Some(
      baseDirectory.value / "webpack-opt.config.js"),
    macroAnnotationSettings
  )
