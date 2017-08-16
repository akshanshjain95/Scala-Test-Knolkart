lazy val common = Seq(
  name := "Scala-Test-Knolkart",
  version := "1.0",
  scalaVersion := "2.12.1",
  libraryDependencies += "org.scalatest" % "scalatest_2.12" % "3.0.3" % "test"
)


lazy val root = (project in file("."))
  .aggregate(accounts, apiResources, dashboard, inventory, checkout, notification)
    .dependsOn(accounts, apiResources, dashboard, inventory, checkout, notification)
  .settings(
    aggregate in update := false
  )

lazy val accounts = project
  .settings(
    commonSettings,
    libraryDependencies += "org.mindrot" % "jbcrypt" % "0.4"
  )

lazy val apiResources = project
  .settings(
    commonSettings
  )

lazy val dashboard = project
  .settings(
    commonSettings
  )

lazy val inventory = project
  .settings(
    commonSettings
  )

lazy val checkout = project
    .dependsOn(inventory)
  .settings(
    commonSettings
  )

lazy val notification = project
  )