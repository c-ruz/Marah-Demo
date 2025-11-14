package model.base

import model.joker.Joker
import model.rank.Rank
import model.suit.Suit


/**
 * Represents a playing card with a rank and a suit.
 *
 * A `Card` combines a `Rank` and a `Suit` to define its characteristics in a 
 * card game. The `Rank` signifies the value or order of the card, while the 
 * `Suit` represents its category or symbol.
 *
 * @constructor Creates a card with the specified rank and suit.
 * @param rank The rank of the card, indicating its value or order.
 * @param suit The suit of the card, representing its category or symbol.
 */
class Card(val rank: Rank, val suit: Suit) {
  
  override def toString: String = s"${rank.toString} of ${suit.toString}"
  
  /**
   * Applies a score transformation based on the card's rank, suit, and a list of jokers.
   *
   * The method begins by calculating an initial score using the card's rank value and the 
   * provided score. It then iteratively applies transformations to the score using each 
   * joker from the list, influencing the rank and suit scoring logic.
   *
   * @param score  the initial `Score` object to be transformed
   * @param jokers a list of `Joker` objects that influence the scoring logic
   * @return the final `Score` object after applying the transformations based on rank, suit, and jokers
   */
  def applyScore(score: Score, jokers: List[Joker]): Score = {
    val applyCardScore = new Score(score.chips + this.rank.value, score.multi)
    jokers.foldLeft(applyCardScore)((acc, joker) => rank.applyScore(suit.applyScore(acc, joker), joker))
  }
}
