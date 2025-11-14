package model.suit.suits

import model.base.Score
import model.joker.Joker
import model.suit.Suit

object Spades extends Suit {

  override def applyScore(score: Score, joker: Joker): Score =
    joker.applySpades(score)

  override def toString: String = "Spades"
}
