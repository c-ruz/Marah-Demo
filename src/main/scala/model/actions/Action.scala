package model.actions

import api.ActionResult
import controller.GameController

import java.util.UUID

trait Action {
  val id: String = UUID.randomUUID().toString
  val name: String

  def doAction(c: GameController): ActionResult
}
