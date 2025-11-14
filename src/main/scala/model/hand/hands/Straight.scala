package model.hand.hands

import model.base.{Card, Score}
import model.hand.APokerHand
import model.joker.Joker

/**
 * Represents a Straight poker hand combination.
 *
 * A `Straight` is a hand of five cards in sequential rank, regardless of their suits.
 * This class extends `APokerHand` and implements the evaluation logic to determine
 * whether a given list of cards satisfies the conditions for a Straight hand.
 *
 * This hand is scored with 30 chips and a multiplier of 4.
 */
class Straight extends APokerHand(new Score(30,4)) {
  
  /**
   * Evaluates whether the given list of cards satisfies the condition for
   * a Straight poker hand. A Straight hand consists of five cards in sequential
   * rank, regardless of their suits.
   *
   * @param cards The list of cards to evaluate, represented as a `List[Card]`. 
   *              It is expected that the list contains exactly five cards.
   * @return `true` if the list of cards satisfies the criteria for a Straight hand;
   *         `false` otherwise.
   */
  override def check(cards: List[Card]): Boolean = {
    // Get all ranks in ascending order
    val sortedRanks = cards.sortBy(_.rank.order)

    // Check if ranks are sequential
    if (sortedRanks.size != 5) return false
    sortedRanks.sliding(2).forall { case List(c1, c2) => c2.rank.order - c1.rank.order == 1 }
  }

  override def applyScore(score: Score, joker: Joker): Score =
    joker.applyStraight(score)
}
