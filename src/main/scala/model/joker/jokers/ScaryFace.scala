package model.joker.jokers

import model.base.Score
import model.joker.AJoker

class ScaryFace extends AJoker {
  override def toString: String = "Scary Face"

  override def applyFace(score: Score): Score = new Score(score.chips + 30, score.multi)

  def description: String = "Adds 30 chips for each face rank played"
}
