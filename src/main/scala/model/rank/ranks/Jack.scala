package model.rank.ranks

import model.base.Score
import model.joker.Joker
import model.rank.FaceRank

/**
 * Represents the Jack rank, which is categorized as a face rank within a hierarchy of ranks.
 *
 * The */
object Jack extends FaceRank {

  /**
   * Returns the order of this rank. The order defines the specific sequencing
   * or position of the rank within a defined hierarchy or structure.
   *
   * @return the integer representing the order of this rank
   */
  override def order: Int = 11

  /**
   * Returns the value associated with this rank. The value typically represents
   * a numerical characteristic or property that differentiates this rank within
   * the hierarchy.
   *
   * @return the integer representing the value of this rank
   */
  override def value: Int = 10

  /**
   * Applies the scoring logic specific to the Jack rank. This method modifies the 
   * given Score object by utilizing the Joker's `applyJack` method prior to invoking 
   * the superclass's scoring logic.
   *
   * @param score the initial score to be transformed
   * @param joker the Joker instance providing Jack-specific scoring logic
   * @return the updated Score object after applying Jack-specific and superclass scoring logic
   */
  override def applyScore(score: Score, joker: Joker): Score = 
    super.applyScore(joker.applyJack(score), joker)
}
