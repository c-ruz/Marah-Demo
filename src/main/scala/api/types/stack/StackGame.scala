package api.types.stack

import api.types.grid.GridGame
import api.types.grid.components.Cell
import api.types.stack.components.{Alignment, Horizontal, StackCell, Vertical}

abstract class StackGame extends GridGame {

  /**
   * Defines the direction the cells will be rendered.
   * It can be either "vertical" or "horizontal"
   */
  def direction: Alignment

  def gridSize: (Int, Int) = direction match {
    case Vertical => (1, cells.length)
    case Horizontal => (cells.length, 1)
  }

  override def cells: List[Cell] = stack.zipWithIndex.map { case (cell, index) => Cell(
      label = cell.label, 
      x = if direction == Horizontal then index else 0, 
      y = if direction == Vertical then index else 0, 
      entities = cell.entities, 
      actions = cell.actions, 
      img = cell.img
    )
  }
  
  def stack: List[StackCell]
}
