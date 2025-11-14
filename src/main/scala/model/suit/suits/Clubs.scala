package model.suit.suits

import model.base.Score
import model.joker.Joker
import model.suit.Suit

object Clubs extends Suit {

  override def applyScore(score: Score, joker: Joker): Score = {
    joker.applyClubs(score)
  }

  override def toString: String = "Clubs"
}
