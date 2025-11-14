package model.hand.hands

import model.base.{Card, Score}
import model.hand.APokerHand
import model.joker.Joker

/**
 * Represents a Straight Flush poker hand combination.
 *
 * A `StraightFlush` is a hand containing five cards of sequential rank, all in the same suit.
 * This class extends `APokerHand` and implements the logic to verify whether a given list 
 * of cards satisfies the condition of being a Straight Flush.
 */
class StraightFlush extends APokerHand(new Score(100, 8)) {
  
  private val flush: Flush = new Flush
  private val straight: Straight = new Straight

  /**
   * Evaluates whether the given list of cards satisfies the criteria for a Straight Flush hand.
   *
   * A Straight Flush hand consists of five cards in sequential rank and all from the same suit.
   * This method combines the evaluation logic of both Flush and Straight hands to determine
   * if the cards meet the criteria for this specific poker hand combination.
   *
   * @param cards The list of cards to evaluate, represented as a `List[Card]`.
   *              It is expected that the list contains exactly five cards.
   * @return `true` if the list of cards satisfies the conditions for a Straight Flush hand;
   *         `false` otherwise.
   */
  override def check(cards: List[Card]): Boolean = {
    flush.check(cards) && straight.check(cards)
  }

  override def applyScore(score: Score, joker: Joker): Score = {
    val straightFlush = joker.applyStraightFlush(score)
    val flush = joker.applyFlush(straightFlush)
    joker.applyStraight(flush)
  }
}
