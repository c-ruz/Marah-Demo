package model.hand.hands

import model.base.{Card, Score}
import model.hand.APokerHand
import model.joker.Joker

/**
 * Represents the Three of a Kind poker hand combination.
 *
 * A `ThreeOfAKind` is a poker hand where exactly three cards have the same rank.
 * This class extends `APokerHand` and implements the logic to determine
 * whether a given list of cards satisfies the conditions for a Three of a Kind.
 *
 * This hand is scored with 30 chips and a multiplier of 3.
 */
class ThreeOfAKind extends APokerHand(new Score(30,3)) {

  /**
   * Evaluates whether the given list of cards satisfies the conditions for
   * a Three of a Kind poker hand. A Three of a Kind hand consists of exactly
   * three cards with the same rank.
   *
   * @param cards The list of cards to evaluate, represented as a `List[Card]`.
   *              The list must contain at least three cards.
   * @return `true` if the list of cards contains at least three cards with the same rank;
   *         `false` otherwise.
   */
  override def check(cards: List[Card]): Boolean = {
    if (cards.length < 3) return false

    // Check if any rank appears at least 4 times
    cards.groupBy(_.rank).values.exists(_.length == 3)
  }

  override def scoringCards(cards: List[Card]): List[Card] =
    cards.groupBy(_.rank).values.filter(_.length == 3).flatten.toList

  override def applyScore(score: Score, joker: Joker): Score =
    joker.applyThreeOfAKind(score)
}
