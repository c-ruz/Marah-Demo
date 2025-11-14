package model.joker.jokers

import model.base.Score
import model.joker.AJoker

class EvenSteven extends AJoker {
  override def toString: String = "Even Steven"
  
  override def applyEven(score: Score): Score = new Score(score.chips, score.multi + 4)

  def description: String = "Adds 4 multi for each even rank played"
}
