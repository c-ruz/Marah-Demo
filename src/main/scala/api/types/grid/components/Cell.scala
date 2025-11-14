package api.types.grid.components

import model.actions.Action

/**
 * Wrapper class for cell to be rendered inside a grid-like game.
 * @param label Optional label to be shown inside the cell.
 * @param x The x coordinate of the cell. This value defines the 
 *          horizontal position of the cell, based on the grid size
 *          defined in [[GridGame]].
 * @param y The y coordinate of the cell. This value defines the 
 *          vertical position of the cell, based on the grid size
 *          defined in [[GridGame]].
 * @param entities List of [[CellEntity]] to be rendered inside the cell.
 * @param actions List of [[Action]] that will be shown inside a menu when
 *                clicking on the cell.
 * @param img Optional image to be rendered as a background of the cell.
 *             If not provided, the cell will be rendered as a solid color.
 */
case class Cell(
    label: Option[String],
    x: Int,
    y: Int,
    entities: List[CellEntity],
    actions: List[Action],
    img: Option[String]
)
