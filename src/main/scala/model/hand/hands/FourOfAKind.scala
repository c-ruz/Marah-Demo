package model.hand.hands

import model.base.{Card, Score}
import model.hand.APokerHand
import model.joker.Joker

/**
 * Represents the Four of a Kind poker hand combination.
 *
 * A `FourOfAKind` is a poker hand where at least four cards have the same rank.
 * This class extends `APokerHand` and implements the logic to determine
 * whether a given list of cards satisfies the conditions for a Four of a Kind.
 *
 * @constructor Initializes a `FourOfAKind` instance with a predefined score.
 */
class FourOfAKind extends APokerHand(new Score(60,7)){

  /**
   * Determines whether the given list of cards satisfies the condition for
   * a Four of a Kind poker hand. A Four of a Kind hand consists of at least
   * four cards with the same rank.
   *
   * @param cards The list of cards to evaluate, represented as a `List[Card]`.
   * @return `true` if the list of cards contains at least four cards with the same rank;
   *         `false` otherwise.
   */
  override def check(cards: List[Card]): Boolean = {
    if (cards.length < 4) return false

    // Check if any rank appears at least 4 times
    cards.groupBy(_.rank).values.exists(_.length == 4)
  }

  override def applyScore(score: Score, joker: Joker): Score =
    joker.applyFourOfAKind(score)

  override def scoringCards(cards: List[Card]): List[Card] = 
    cards.groupBy(_.rank).values.filter(_.length == 4).flatten.toList
}
