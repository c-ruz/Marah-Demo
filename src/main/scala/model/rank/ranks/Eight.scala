package model.rank.ranks

import model.base.Score
import model.joker.Joker
import model.rank.EvenRank

/**
 * Represents a rank with the ordinal order and value of eight, extending the 
 * hierarchy of even ranks. This class defines a specific rank within the 
 * context of a structured ranking system.
 */
object Eight extends EvenRank {

  /**
   * Returns the order of this rank. The order defines the specific sequencing
   * or position of the rank within a defined hierarchy or structure.
   *
   * @return the integer representing the order of this rank
   */
  override def order: Int = 8

  /**
   * Returns the value associated with this rank. The value typically represents
   * a numerical characteristic or property that differentiates this rank within
   * the hierarchy.
   *
   * @return the integer representing the value of this rank
   */
  override def value: Int = 8

  /**
   * Applies the scoring logic specific to the rank Eight. This method modifies 
   * the given Score object by utilizing the Joker's `applyEight` method prior 
   * to invoking the superclass's scoring logic for even ranks.
   *
   * @param score the initial score to be transformed
   * @param joker the Joker instance providing logic for transforming the score 
   *              specific to the rank Eight and other even rank logic
   * @return the updated Score object after applying the Eight-specific and 
   *         even-rank logic
   */
  override def applyScore(score: Score, joker: Joker): Score = 
    super.applyScore(joker.applyEight(score), joker)
}
