package api.types.grid

import api.types.base.Game
import api.types.grid.components.Cell

abstract class GridGame extends Game {

  /** Defines the grid to be rendered. First value defines the horizontal span
    * of the grid, second value defines the vertical span of the grid.
    */
  def gridSize: (Int, Int)

  /** List of [[Cell]] to be rendered inside the grid.
    */
  def cells: List[Cell]
}
