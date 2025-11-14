package model.actions

import api.{ActionResult, Success}
import controller.GameController
import model.base.Card

class ChooseCard(card: Card) extends Action {

  val name: String = "Choose Card"

  def doAction(c: GameController): ActionResult = {
    c.chosenCards = c.chosenCards :+ card
    c.hand = c.hand.filterNot(_ == card)
    c._topBarMessage = None
    Success("Card Chosen")
  }
}
