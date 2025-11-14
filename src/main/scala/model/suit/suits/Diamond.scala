package model.suit.suits

import model.base.Score
import model.joker.Joker
import model.suit.Suit

object Diamond extends Suit {
  override def applyScore(score: Score, joker: Joker): Score =
    joker.applyDiamonds(score)

  override def toString: String = "Diamonds"
}
