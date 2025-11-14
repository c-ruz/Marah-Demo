package model.rank.ranks

import model.base.Score
import model.joker.Joker
import model.rank.EvenRank


/**
 * Represents a rank with the ordinal order and value of four, extending the 
 * hierarchy of even ranks. This class defines a specific rank within the 
 * context of a structured ranking system.
 */
object Four extends EvenRank {

  /**
   * Returns the order of this rank. The order defines the specific sequencing 
   * or position of the rank within a defined hierarchy or structure.
   *
   * @return the integer representing the order of this rank
   */
  override def order: Int = 4

  /**
   * Returns the value associated with this rank. The value typically represents
   * a numerical characteristic or property that differentiates this rank within 
   * the hierarchy.
   *
   * @return the integer representing the value of this rank
   */
  override def value: Int = 4

  /**
   * Applies the scoring logic specific to the rank "Four". This method utilizes 
   * the `joker`'s `applyFour` method to transform the given `Score` object before 
   * applying the superclass scoring logic for even ranks.
   *
   * @param score the initial score to be transformed
   * @param joker the Joker instance providing the scoring logic specific to the "Four" rank
   * @return the updated Score object after applying "Four"-specific and superclass scoring logic
   */
  override def applyScore(score: Score, joker: Joker): Score = 
    super.applyScore(joker.applyFour(score), joker)
}
