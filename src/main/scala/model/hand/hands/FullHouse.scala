package model.hand.hands

import model.base.{Card, Score}
import model.hand.APokerHand
import model.joker.Joker

/**
 * Represents the Full House poker hand combination.
 *
 * A `FullHouse` is a poker hand that consists of three cards of one rank
 * and two cards of another rank. This class extends the `APokerHand` class
 * and utilizes two other poker hand combinations, `ThreeOfAKind` and `Pair`,
 * to evaluate whether a given list of cards satisfies the Full House criteria.
 *
 * This hand is scored with 40 chips and a multiplier of 4.
 *
 * @constructor Initializes the `FullHouse` with a predefined score.
 *              Combines the logic of a `ThreeOfAKind` and a `Pair` to
 *              determine if the hand is a Full House.
 */
class FullHouse extends APokerHand(new Score(40,4)) {
  private val threeOfAKind = new ThreeOfAKind
  private val pair = new Pair

  /**
   * Evaluates whether the given list of cards satisfies the conditions for a Full House hand.
   *
   * A Full House hand consists of three cards of one rank and two cards of another rank.
   * This method checks if the provided list of cards matches this combination by verifying
   * the presence of exactly two groups of cards with at least two cards having the same rank,
   * as well as confirming the conditions for both a Three of a Kind and a Pair.
   *
   * @param cards The list of cards to evaluate, represented as a `List[Card]`. 
   *              It is expected that the list contains at least five cards.
   * @return `true` if the list of cards fulfills the criteria for a Full House;
   *         `false` otherwise.
   */
  override def check(cards: List[Card]): Boolean = {
    cards.groupBy(_.rank).values.count(_.length >= 2) == 2 
      && threeOfAKind.check(cards) 
      && pair.check(cards)
  }

  override def applyScore(score: Score, joker: Joker): Score =
    joker.applyFullHouse(score)
}
