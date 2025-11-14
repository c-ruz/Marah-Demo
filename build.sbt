import scala.collection.Seq
import scala.sys.process.*

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.7"

lazy val root = (project in file("."))
  .settings(
    name := "marah"
  )

val PekkoVersion = "1.1.3"
val PekkoHttpVersion = "1.2.0"

libraryDependencies ++= Seq(
  "org.scalameta" %% "munit" % "1.1.1" % Test,
  "org.apache.pekko" %% "pekko-actor-typed" % PekkoVersion,
  "org.apache.pekko" %% "pekko-stream" % PekkoVersion,
  "org.apache.pekko" %% "pekko-http" % PekkoHttpVersion,
  "org.apache.pekko" %% "pekko-http-spray-json" % PekkoHttpVersion,
  "org.apache.pekko" %% "pekko-http-cors" % PekkoHttpVersion,
)

lazy val buildFrontend =
  taskKey[Unit]("Build frontend with npm and copy to public")

buildFrontend := {
  val log = streams.value.log
  val base = baseDirectory.value
  val frontendDir = base / "frontend"
  val outputDir = frontendDir / "dist"
  val targetDir = base / "src" / "main" / "resources" / "public"

  // Detectar si estamos en Windows
  val os = System.getProperty("os.name").toLowerCase
  val isWindows = os.contains("win")

  // Crear los comandos apropiados para el SO
  // En Windows, usamos "cmd /c" para ejecutar el comando en el shell de Windows.
  // "cmd /c" ejecuta el comando y luego termina.
  val npmInstall = if (isWindows) "cmd /c npm install" else "npm install"
  val npmBuild = if (isWindows) "cmd /c npm run build" else "npm run build"

  log.info("🔧 Building frontend...")

  // Ejecutar los comandos específicos del SO
  if (Process(npmInstall, frontendDir).! != 0) {
    sys.error("¡Falló 'npm install'!")
  }
  if (Process(npmBuild, frontendDir).! != 0) {
    sys.error("¡Falló 'npm run build'!")
  }

  log.info(s"📁 Copying frontend dist to $targetDir...")
  IO.delete(targetDir)
  IO.copyDirectory(outputDir, targetDir)

  log.info("✅ Frontend build complete.")
}