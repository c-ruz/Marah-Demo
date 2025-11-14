package model.hand.hands

import model.base.{Card, Score}
import model.hand.APokerHand
import model.joker.Joker

/**
 * Represents the High Card poker hand combination.
 *
 * A `HighCard` is the lowest ranking poker hand, defined as any hand that does 
 * not qualify for any other poker hand combinations such as Pair, Flush, or Full House.
 * This class extends `APokerHand` and always evaluates to `true` since every valid hand
 * can qualify as a High Card if no other specific combination is met.
 *
 * This hand is scored with 5 chips and a multiplier of 1.
 */
class HighCard extends APokerHand(new Score(5,1)) {

  /**
   * Evaluates whether the given list of cards satisfies the conditions
   * for this specific poker hand combination.
   *
   * For the `HighCard` hand, this method always returns `true` since every
   * valid poker hand without other specific combinations qualifies as a High Card.
   *
   * @param cards The list of cards to evaluate, represented as a `List[Card]`.
   *              It is expected that the list contains a valid number of cards
   *              for a poker hand, although detailed validation is typically
   *              handled externally.
   * @return `true`, as every valid hand meets the criteria for a High Card.
   */
  override def check(cards: List[Card]): Boolean = {
    true
  }

  override def applyScore(score: Score, joker: Joker): Score =
    joker.applyHighCard(score)

  override def scoringCards(cards: List[Card]): List[Card] =
    cards.maxBy(_.rank.value) :: Nil
}
