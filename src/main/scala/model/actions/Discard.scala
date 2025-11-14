package model.actions

import api.{ActionResult, Success}
import controller.{Deck, GameController}

class Discard extends Action {

  val name: String = "Discard"

  def doAction(c: GameController): ActionResult = {
    c.chosenCards = List()
    c.hand = c.hand ++ Deck.draw(8 - c.hand.length)
    Success("Discarded")
  }
}
