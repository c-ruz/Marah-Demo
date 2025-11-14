package model.joker

import model.base.Score

abstract class AJoker extends Joker {

  override def applyScore(score: Score): Score = score

  override def applyDiamonds(score: Score): Score = score

  override def applyClubs(score: Score): Score = score

  override def applySpades(score: Score): Score = score

  override def applyHearts(score: Score): Score = score

  override def applyStraightFlush(score: Score): Score = score

  override def applyFourOfAKind(score: Score): Score = score

  override def applyFullHouse(score: Score): Score = score

  override def applyFlush(score: Score): Score = score

  override def applyStraight(score: Score): Score = score

  override def applyThreeOfAKind(score: Score): Score = score

  override def applyTwoPair(score: Score): Score = score

  override def applyPair(score: Score): Score = score

  override def applyHighCard(score: Score): Score = score

  override def applyTwo(score: Score): Score = score

  override def applyThree(score: Score): Score = score

  override def applyFour(score: Score): Score = score

  override def applyFive(score: Score): Score = score

  override def applySix(score: Score): Score = score

  override def applySeven(score: Score): Score = score

  override def applyEight(score: Score): Score = score

  override def applyNine(score: Score): Score = score

  override def applyTen(score: Score): Score = score

  override def applyJack(score: Score): Score = score

  override def applyQueen(score: Score): Score = score

  override def applyKing(score: Score): Score = score

  override def applyAce(score: Score): Score = score

  override def applyFace(score: Score): Score = score

  override def applyOdd(score: Score): Score = score

  override def applyEven(score: Score): Score = score
}
