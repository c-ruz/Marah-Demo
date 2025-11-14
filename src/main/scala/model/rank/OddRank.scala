package model.rank

import model.base.Score
import model.joker.Joker

/**
 * Represents an abstract base class for ranks categorized as odd ranks.
 *
 * `OddRank` extends the `Rank` trait and provides specific scoring logic
 * applicable to all odd ranks. It leverages the `Joker`'s `applyOdd` method
 * to transform the given score accordingly.
 *
 * In the hierarchy of ranks, this class is typically extended by specific
 * odd-ranked types such as Ace or other odd-valued ranks.
 *
 * @note The `applyScore` method of this class delegates the scoring logic
 *       to the `Joker`'s `applyOdd` method.
 */
abstract class OddRank extends Rank {
  /**
   * Applies a scoring transformation based on the given score and the joker's logic 
   * for odd ranks.
   *
   * This method leverages the joker's `applyOdd` function to transform the initial 
   * score according to specific logic tailored for odd ranks.
   *
   * @param score the initial score to be transformed
   * @param joker the joker instance providing additional scoring logic
   * @return the updated `Score` after applying the odd rank transformation logic
   */
  override def applyScore(score: Score, joker: Joker): Score = {
    joker.applyOdd(score)
  }
}
