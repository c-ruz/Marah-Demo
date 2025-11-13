package api.types.stack.components

import api.types.grid.components.CellEntity
import model.actions.Action

/**
 * Wrapper class for cell to be rendered inside a stack-like game.
 * @param label Optional label to be shown inside the cell.
 * @param entities List of [[CellEntity]] to be rendered inside the cell.
 * @param actions List of [[Action]] that will be shown inside a menu when
 *                clicking on the cell.
 * @param img Optional image to be rendered as a background of the cell.
 *             If not provided, the cell will be rendered as a solid color.
 */
case class StackCell(
    label: Option[String] = None,
    entities: List[CellEntity] = List.empty,
    actions: List[Action] = List.empty,
    img: Option[String] = None
)
