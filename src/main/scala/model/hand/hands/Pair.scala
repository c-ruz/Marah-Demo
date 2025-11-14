package model.hand.hands

import model.base.{Card, Score}
import model.hand.APokerHand
import model.joker.Joker

class Pair extends APokerHand(new Score(10,2)){

  /**
   * Evaluates whether the provided list of cards meets the criteria for a Pair hand.
   *
   * A Pair hand consists of at least two cards with the same rank. This method checks
   * the given list of cards to determine if such a combination exists.
   *
   * @param cards The list of cards to evaluate, represented as a `List[Card]`.
   *              It is expected that the list contains at least two cards.
   * @return `true` if the list contains at least one pair of cards with the same rank;
   *         `false` otherwise.
   */
  override def check(cards: List[Card]): Boolean = {
    if (cards.length < 2) return false

    cards.groupBy(_.rank).values.exists(_.length == 2)
  }

  override def applyScore(score: Score, joker: Joker): Score =
    joker.applyPair(score)

  override def scoringCards(cards: List[Card]): List[Card] = {
      cards.groupBy(_.rank).values.find(_.length == 2).get
  }
}
