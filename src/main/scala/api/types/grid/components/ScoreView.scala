package api.types.grid.components

/**
 * Wrapper for any score shown in the bottom menu of 
 * the visualizer. The score will be shown on the right
 * side of the menu, with the name, on the left, followed
 * by the value.
 * @param name Name of the score
 * @param value Content of the score
 *              
 * @example {"name": "Character", "value": s"HP: ${character.hp} / ${character.maxHp}"}
 */
case class ScoreView(
    name: String,
    value: String
)
