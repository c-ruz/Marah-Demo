package api.types.grid.components

/** Wrapper for an attribute to be shown inside a tooltip when hovering a cell
  * entity.
  * @param name
  *   Name of the attribute
  * @param value
  *   Value of the attribute
  */
case class CellEntityAttribute(
    name: String,
    value: String
)
