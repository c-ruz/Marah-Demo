package model.actions

import api.{ActionResult, Success}
import controller.GameController
import model.base.Card

class RemoveCard(card: Card) extends Action {

  val name: String = "Remove Card"

  def doAction(c: GameController): ActionResult = {
    c.chosenCards = c.chosenCards.filterNot(_ == card)
    c.hand = c.hand :+ card
    Success("Card Removed")
  }
}
