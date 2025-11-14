package model.suit.suits

import model.base.Score
import model.joker.Joker
import model.suit.Suit

object Hearts extends Suit {
  override def applyScore(score: Score, joker: Joker): Score =
    joker.applyHearts(score)

  override def toString: String = "Hearts"
}
