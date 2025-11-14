package model.hand.hands

import model.base.{Card, Score}
import model.hand.APokerHand
import model.joker.Joker

/**
 * Represents the Two Pair poker hand combination.
 *
 * A `TwoPair` consists of two distinct pairs of cards with the same rank.
 * This class extends `APokerHand` and implements the logic to evaluate if
 * a given list of cards satisfies the conditions for a Two Pair poker hand.
 *
 * This hand is scored with 20 chips and a multiplier of 2.
 */
class TwoPair extends APokerHand(new Score(20,2)) {
  
  override def check(cards: List[Card]): Boolean = {
    if (cards.length < 4) return false

    // Count the number of pairs (ranks that appear exactly twice)
    cards.groupBy(_.rank).values.count(_.length == 2) == 2
  }

  override def scoringCards(cards: List[Card]): List[Card] = {
      cards.groupBy(_.rank).values.filter(_.length == 2).flatten.toList
  }

  override def applyScore(score: Score, joker: Joker): Score = joker.applyTwoPair(score)
}
