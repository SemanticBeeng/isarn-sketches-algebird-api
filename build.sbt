name := "isarn-sketches-algebird-api"

organization := "org.isarnproject"

bintrayOrganization := Some("isarn")

version := "0.1.1"

scalaVersion := "2.11.8"

crossScalaVersions := Seq("2.10.6", "2.11.8")

useGpg := true

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

def commonSettings = Seq(
  libraryDependencies ++= Seq(
    "org.isarnproject" %% "isarn-sketches" % "0.1.1",
    "com.twitter" %% "algebird-core" % "0.13.0",
    "org.isarnproject" %% "isarn-scalatest" % "0.0.2" % Test,
    "org.scalatest" %% "scalatest" % "2.2.4" % Test,
    "org.apache.commons" % "commons-math3" % "3.6.1" % Test)
)

seq(commonSettings:_*)

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

scalacOptions in (Compile, doc) ++= Seq("-doc-root-content", baseDirectory.value+"/root-doc.txt")

site.settings

site.includeScaladoc()

// Re-enable if/when we want to support gh-pages w/ jekyll
// site.jekyllSupport()

ghpages.settings

git.remoteRepo := "git@github.com:isarn/isarn-sketches-algebird-api.git"
