// xsbt clean unidoc previewSite
// xsbt clean unidoc ghpagesPushSite
// xsbt -Dsbt.global.base=/home/eje/.sbt/sonatype +publish

name := "isarn-sketches-algebird-api"

organization := "org.isarnproject"

version := "0.1.3-SNAPSHOT"

scalaVersion := "2.12.13"

crossScalaVersions := Seq("2.11.12", "2.12.13")

pomIncludeRepository := { _ => false }

publishMavenStyle := true

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

licenses += ("Apache-2.0", url("http://opensource.org/licenses/Apache-2.0"))

homepage := Some(url("https://github.com/isarn/isarn-sketches-algebird-api"))

scmInfo := Some(
  ScmInfo(
    url("https://github.com/isarn/isarn-sketches-algebird-api"),
    "scm:git@github.com:isarn/isarn-sketches-algebird-api.git"
  )
)

developers := List(
  Developer(
    id    = "erikerlandson",
    name  = "Erik Erlandson",
    email = "eje@redhat.com",
    url   = url("https://erikerlandson.github.io/")
  )
)

libraryDependencies ++= Seq(
  "org.isarnproject" %% "isarn-sketches" % "0.1.2" % Provided,
  "com.twitter" %% "algebird-core" % "0.13.8" % Provided,
  "org.isarnproject" %% "isarn-scalatest" % "0.0.3" % Test,
  "org.scalatest" %% "scalatest" % "3.2.5" % Test,
  "org.apache.commons" % "commons-math3" % "3.6.1" % Test)

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

//scalacOptions in (Compile, doc) ++= Seq("-doc-root-content", baseDirectory.value+"/root-doc.txt")

//enablePlugins(ScalaUnidocPlugin, GhpagesPlugin)

//siteSubdirName in ScalaUnidoc := "latest/api"

//addMappingsToSiteDir(mappings in (ScalaUnidoc, packageDoc), siteSubdirName in ScalaUnidoc)

git.remoteRepo := "git@github.com:isarn/isarn-sketches-algebird-api.git"
