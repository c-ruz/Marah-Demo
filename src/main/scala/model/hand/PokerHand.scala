package model.hand

import model.base.{Card, Score}
import model.joker.Joker

/**
 * Represents a poker hand in a card game.
 *
 * This trait encapsulates the behavior and properties required to evaluate
 * and score a poker hand based on a collection of cards. Implementations
 * should define the rules for determining whether a given set of cards
 * constitutes a valid hand and calculate the score for that hand.
 */
trait PokerHand {

  /**
   * Checks whether the given list of cards satisfies the criteria for a valid poker hand.
   *
   * This method evaluates the provided list of cards to determine if it constitutes
   * a valid combination based on the specific rules defined in the implementing class.
   *
   * @param cards A list of `Card` objects representing the poker hand to be evaluated
   * @return true if the cards meet the criteria for a valid hand, false otherwise
   */
  def check(cards: List[Card]): Boolean

  /**
   * Applies the scoring logic of the `Joker` to the given `Score` and returns the updated score.
   *
   * This method takes a base score and a `Joker` instance that encapsulates custom scoring
   * logic. The `Joker` modifies the base score according to specific rules defined
   * within its implementation, and the resulting score is returned.
   *
   * @param score The initial `Score` to be updated by applying the `Joker` logic.
   * @param joker The `Joker` that defines the rules for modifying the score.
   * @return The updated `Score` after applying the `Joker` logic to the given score.
   */
  def applyScore(score: Score, joker: Joker): Score

  /**
   * Processes and returns a list of cards for scoring purposes in a poker hand.
   *
   * This method takes a list of `Card` objects and applies logic to prepare or
   * identify the cards that are relevant for scoring in the context of a poker hand.
   *
   * @param cards A list of `Card` objects representing the poker hand.
   * @return A list of `Card` objects to be used for scoring.
   */
  def scoringCards(cards: List[Card]): List[Card]

  /**
   * Computes and returns the score of the current poker hand.
   *
   * This method evaluates the poker hand according to specific rules and
   * scoring criteria, returning a `Score` object that encapsulates the
   * chips and multiplier values associated with the hand's performance.
   *
   * @return the `Score` object representing the evaluated score of the poker hand
   */
  def score: Score
}
