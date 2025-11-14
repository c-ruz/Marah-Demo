package controller

import api.types.grid.components.{CellEntity, CellEntityAttribute, ScoreView}
import api.types.stack.StackGame
import api.types.stack.components.{Alignment, StackCell, Vertical}
import model.actions.Action
import model.base.Card
import model.joker.Joker
import model.joker.jokers.{DeviousJoker, EvenSteven, GreedyJoker, ScaryFace}

class GameController extends StackGame {

  var jokers: List[Joker] = List(
    new EvenSteven,
    new DeviousJoker,
    new GreedyJoker,
    new ScaryFace
  )

  var chosenCards = List()

  var hand: List[Card] = Deck.draw(8)

  /** Defines the direction the cells will be rendered. It can be either
    * "vertical" or "horizontal"
    */
  def direction: Alignment = Vertical

  def stack: List[StackCell] = List(
    StackCell(
      label = Some("Jokers"),
      entities = jokers.map(j =>
        CellEntity(
          name = j.getClass.getSimpleName,
          attributes = List(
            CellEntityAttribute("Description", j.description)
          ),
          img = Some(j.getClass.getSimpleName.toLowerCase().replaceAll(" ", "") + ".png")
        )
      )
    ),
    StackCell(
      label = Some("Chosen Cards"),
      entities = chosenCards.map(c =>
        CellEntity(
          name = c.toString,
          img = Some(c.toString.toLowerCase().replaceAll(" ", "") + ".png")
        )
      )
    ),
    StackCell(
      label = Some("Hand"),
      entities = hand.map(c =>
        CellEntity(
          name = c.toString,
          img = Some(c.toString.toLowerCase().replaceAll(" ", "") + ".png")
        )
      )
    )
  )

  /** List of [[ScoreView]] to be shown in the bottom menu.
    */
  def score: List[ScoreView] = List()

  /** Message for feedback to the user, rendered in the top bar of the
    * visualizer. Use it to provide the user with directions regarding the
    * current state of the game.
    */
  def topBarMessage: Option[String] = None

  /** List of [[Action]] to be shown in the bottom menu of the visualizer.
    */
  def menuActions: List[Action] = List()
}
