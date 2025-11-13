package api

import api.GameApi.{as, complete, entity, get, path, post}
import api.types.base.Game
import api.types.grid.components.{Cell, CellEntity, CellEntityAttribute, ScoreView}
import api.types.grid.{GridGame, GridGameSnapshot}
import controller.GameController
import model.actions.Action
import org.apache.pekko.actor.typed.ActorSystem
import org.apache.pekko.actor.typed.scaladsl.Behaviors
import org.apache.pekko.http.scaladsl.Http
import org.apache.pekko.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import org.apache.pekko.http.scaladsl.server.Directives
import spray.json.{DefaultJsonProtocol, JsNumber, JsObject, JsString, JsValue, RootJsonFormat, deserializationError, enrichAny}

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn
import scala.language.implicitConversions

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {

  implicit val actionFormat: RootJsonFormat[Action] =
    new RootJsonFormat[Action] {
      override def read(json: JsValue): Action =
        deserializationError("Action deserialization not supported")

      override def write(obj: Action): JsValue = JsObject(
        "id" -> JsString(obj.id),
        "name" -> JsString(obj.name)
      )
    }

  implicit val scoreViewFormat: RootJsonFormat[ScoreView] = jsonFormat2(ScoreView.apply)
  implicit val cellEntityAttribute: RootJsonFormat[CellEntityAttribute] = jsonFormat2(CellEntityAttribute.apply)
  implicit val cellEntityFormat: RootJsonFormat[CellEntity] = jsonFormat4(CellEntity.apply)
  implicit val cellViewFormat: RootJsonFormat[Cell] = jsonFormat6(Cell.apply)

  implicit val gameViewFormat: RootJsonFormat[Game] =
    new RootJsonFormat[Game] {
      override def read(json: JsValue): Game =
        deserializationError("Game deserialization not supported")

      override def write(obj: Game): JsValue = obj match {
        case g: GridGame => JsObject(
          "gridSize" -> JsObject(
            "x" -> JsNumber(g.gridSize._1),
            "y" -> JsNumber(g.gridSize._2)
          ),
          "cells" -> g.cells.toJson,
          "scores" -> g.score.toJson,
          "topBarMessage" -> g.topBarMessage.toJson,
          "menuActions" -> g.menuActions.toJson
        )
      }
    }

  implicit val actionResultFormat: RootJsonFormat[ActionResult] =
    new RootJsonFormat[ActionResult] {
      override def read(json: JsValue): ActionResult =
        deserializationError("ActionResult deserialization not supported")

      override def write(obj: ActionResult): JsValue = obj match {
        case Success(message) => JsObject("message" -> JsString(message))
        case Failure(message) => JsObject("error" -> JsString(message))
        case null => JsObject("error" -> JsString("Unknown error"))
      }
    }
}

object GameApi extends Directives with JsonSupport {

  implicit val system: ActorSystem[_] = ActorSystem(Behaviors.empty, "GameApi")
  implicit val executionContext: ExecutionContextExecutor =
    system.executionContext

  private val controller: GameController = new GameController()

  private var controllerSnapshot = new GridGameSnapshot(controller)
  private def resetSnapshot(): Unit = controllerSnapshot = new GridGameSnapshot(controller)

  implicit def string2ActionResult(s: String): ActionResult = Success(s)

  def main(args: Array[String]): Unit = {
    val route = {
      path("state") {
        get {
          resetSnapshot()
          complete(controllerSnapshot)
        }
      } ~
        path("actions") {
          post {
            entity(as[String]) { actionId =>
              val action = controllerSnapshot.findActionById(actionId)
              
              if (action.isDefined) {
                val result: ActionResult = action.get.doAction(controller)
                complete(result)
              } else {
                complete(Failure(s"Action with ID $actionId was not found"))
              }
            }
          }
        }
    } ~
      get {
        pathPrefix("resources" / "static") {
          getFromResourceDirectory("static")
        }
      } ~
      get {
        pathSingleSlash {
          getFromResourceDirectory("public") ~ getFromResource("public/index.html")
        }
      } ~
      get {
        pathPrefix("assets") {
          getFromResourceDirectory("public/assets")
        }
      } ~
      get {
        path("favicon.ico") {
          getFromResource("public/favicon.ico")
        }
      } ~
      get {
        path("default.png") {
          getFromResource("public/default.png")
        }
      } ~
      get {
        path(Remaining) { _ =>
          getFromResource("public/index.html")
        }
      }

    val bindingFuture = Http().newServerAt("localhost", 8080).bind(route)

    println(
      s"Server now online. Please navigate to http://localhost:8080/\nPress RETURN to stop..."
    )
    StdIn.readLine()
    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }
}
