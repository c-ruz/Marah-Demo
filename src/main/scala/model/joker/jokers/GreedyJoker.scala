package model.joker.jokers

import model.base.Score
import model.joker.{AJoker, Joker}

class GreedyJoker extends AJoker {
  override def toString: String = "Greedy Joker"

  override def applyDiamonds(score: Score): Score = new Score(score.chips, score.multi + 3)

  def description: String = "Adds 3 multi for each diamond rank played"
}
