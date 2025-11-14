package model.actions

import api.{ActionResult, Failure, Success}
import controller.{Deck, GameController}
import model.base.Scorer

class Play extends Action {

  val name: String = "Play"

  def doAction(c: GameController): ActionResult = {
    if (c.chosenCards.isEmpty) {
      Failure("Please choose at least one card")
    }
    else {
      val score = Scorer.score(c.chosenCards, c.jockers)
      val total = score.chips * score.multi
      c._score = total
      c._topBarMessage = Some(s"CHIPS = ${score.chips} * MULTI = ${score.multi} = $total")
      c.chosenCards = List()
      c.hand = c.hand ++ Deck.draw(8 - c.hand.length)
      Success("Played")
    }
  }
}
