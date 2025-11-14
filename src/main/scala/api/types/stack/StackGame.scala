package api.types.stack

import api.types.grid.GridGame
import api.types.grid.components.Cell
import api.types.stack.components.StackCell

abstract class StackGame extends GridGame {

  /**
   * Defines the direction the cells will be rendered.
   * It can be either "vertical" or "horizontal"
   */
  def direction: "vertical" | "horizontal"

  def gridSize: (Int, Int) = direction match {
    case "vertical" => (1, cells.length)
    case "horizontal" => (cells.length, 1)
  }

  override def cells: List[Cell] = stack.zipWithIndex.map { case (cell, index) => Cell(
      label = cell.label, 
      x = if direction == "horizontal" then index else 0, 
      y = if direction == "vertical" then index else 0, 
      entities = cell.entities, 
      actions = cell.actions, 
      img = cell.img
    )
  }
  
  def stack: List[StackCell]
}
