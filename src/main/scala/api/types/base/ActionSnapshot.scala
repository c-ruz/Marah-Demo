package api.types.base

import model.actions.Action

trait ActionSnapshot {
  def findActionById(id: String): Option[Action]
}
