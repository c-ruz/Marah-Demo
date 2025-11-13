package api.types.grid.components

import model.actions.Action

/**
 * Wrapper class for an entity to be rendered inside a cell.
 * @param name Name of the entity.
 * @param attributes List of [[CellEntityAttribute]] to be shown with a tooltip
 *                   when hovering the entity.
 * @param actions List of [[Action]] that will be shown inside a menu when
 *                clicking on the entity or the cell that contains it.
 * @param img Optional image to be used to render the entity inside the cell.
 *             If not provided, a default image will be used.
 */
case class CellEntity(
    name: String,
    attributes: List[CellEntityAttribute] = List.empty,
    actions: List[Action] = List.empty,
    img: Option[String] = None
)
