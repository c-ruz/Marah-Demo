package model.hand.hands

import model.base.{Card, Score}
import model.hand.APokerHand
import model.joker.Joker
import model.suit.suits.Spades

/**
 * Represents the Flush poker hand combination.
 *
 * A `Flush` is a hand where all five cards have the same suit, regardless of their ranks.
 * This class extends `APokerHand` and evaluates whether a given list of cards 
 * satisfies the conditions for a Flush.
 *
 * This hand is scored with 35 chips and a multiplier of 4.
 */
class Flush extends APokerHand(new Score(35,4)) {

  /**
   * Evaluates if a given list of cards satisfies the conditions of the hand.
   *
   * This method determines whether the hand meets the specific criteria required
   * for this poker hand type, for instance, evaluating if the cards all belong 
   * to the same suit for a Flush. 
   *
   * @param cards The list of cards to evaluate, represented as a `List[Card]`.
   *              It is expected that the list contains exactly five cards.
   * @return `true` if the list of cards fulfills the criteria for this hand; 
   *         `false` otherwise.
   */
  override def check(cards: List[Card]): Boolean = {
    if (cards.length != 5) return false

    // Check if all cards are of the same suit
    cards.groupBy(_.suit).size == 1
  }

  override def applyScore(score: Score, joker: Joker): Score =
    joker.applyFlush(score)
}
