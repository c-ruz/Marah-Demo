package model.rank

import model.base.Score
import model.joker.Joker

/**
 * Represents an abstract base class for ranks categorized as even ranks.
 *
 * `EvenRank` extends the `Rank` trait and provides specific scoring logic
 * applicable to all even ranks. It utilizes the `Joker`'s `applyEven` method
 * to transform the given score accordingly.
 *
 * In the hierarchy of ranks, this class is typically extended by specific
 * even-ranked types such as Two, Four, or other even-valued ranks.
 *
 * @note The `applyScore` method of this class delegates the scoring logic
 *       to the `Joker`'s `applyEven` method.
 */
abstract class EvenRank extends Rank {
  /**
   * Applies a scoring transformation specific to even ranks using the provided `Joker`.
   *
   * This method utilizes the `joker`'s `applyEven` function to transform the given
   * `Score` object based on the logic defined for even ranks.
   *
   * @param score the initial score to be transformed
   * @param joker the joker instance providing the even rank transformation logic
   * @return the updated `Score` after applying the transformation logic for even ranks
   */
  override def applyScore(score: Score, joker: Joker): Score = joker.applyEven(score)
}
