package api.types.grid

import api.types.base.ActionSnapshot
import api.types.grid.components.{Cell, ScoreView}
import model.actions.Action

class GridGameSnapshot(gridGame: GridGame) extends GridGame, ActionSnapshot {
  def gridSize: (Int, Int) = gridGame.gridSize

  def score: List[ScoreView] = gridGame.score

  def topBarMessage: Option[String] = gridGame.topBarMessage

  val cells: List[Cell] = gridGame.cells

  val menuActions: List[Action] = gridGame.menuActions

  def findActionById(id: String): Option[Action] = {
    cells.flatMap(_.actions)
      .concat(cells.flatMap(_.entities.flatMap(_.actions)))
      .concat(menuActions)
      .find(_.id == id)
  }
}
