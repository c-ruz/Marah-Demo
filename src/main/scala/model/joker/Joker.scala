package model.joker

import model.base.Score

/**
 * Represents logic for applying scores based on various factors in a card game.
 *
 * The `Joker` trait provides methods to modify the scoring system of a card game
 * by applying different scoring rules based on suits, hand combinations, ranks,
 * and other characteristics. Each method takes a `Score` object as input,
 * applies a specific transformation, and returns an updated `Score` object.
 */
trait Joker {
  // Flat score
  def applyScore(score: Score): Score

  // Suit Score Logic
  def applyDiamonds(score: Score): Score
  def applyClubs(score: Score): Score
  def applySpades(score: Score): Score
  def applyHearts(score: Score): Score

  // Hand score logic
  def applyStraightFlush(score: Score): Score
  def applyFourOfAKind(score: Score): Score
  def applyFullHouse(score: Score): Score
  def applyFlush(score: Score): Score
  def applyStraight(score: Score): Score
  def applyThreeOfAKind(score: Score): Score
  def applyTwoPair(score: Score): Score
  def applyPair(score: Score): Score
  def applyHighCard(score: Score): Score

  // Rank score logic
  def applyTwo(score: Score): Score
  def applyThree(score: Score): Score
  def applyFour(score: Score): Score
  def applyFive(score: Score): Score
  def applySix(score: Score): Score
  def applySeven(score: Score): Score
  def applyEight(score: Score): Score
  def applyNine(score: Score): Score
  def applyTen(score: Score): Score
  def applyJack(score: Score): Score
  def applyQueen(score: Score): Score
  def applyKing(score: Score): Score
  def applyAce(score: Score): Score

  // kind of rank score logic
  def applyFace(score: Score): Score
  def applyOdd(score: Score): Score
  def applyEven(score: Score): Score

  override def toString: String = this.getClass.getSimpleName
  def description: String
}
