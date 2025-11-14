package model.rank.ranks

import model.base.Score
import model.joker.Joker
import model.rank.OddRank

/**
 * Represents the rank with the ordinal order and */
object Seven extends OddRank {

  /**
   * Returns the order of this rank. The order defines the specific sequencing
   * or position of the rank within a defined hierarchy or structure.
   *
   * @return the integer representing the order of this rank
   */
  override def order: Int = 7

  /**
   * Returns the value associated with this rank. The value typically represents
   * a numerical characteristic or property that differentiates this rank within
   * the hierarchy.
   *
   * @return the integer representing the value of this rank
   */
  override def value: Int = 7

  /**
   * Applies the scoring logic specific to the Seven rank. This method modifies the 
   * given Score object by utilizing the Joker's `applySeven` method prior to invoking 
   * the superclass's scoring logic.
   *
   * @param score the initial Score object to be transformed
   * @param joker the Joker instance providing Seven-specific scoring logic
   * @return the updated Score object after applying Seven-specific and superclass scoring logic
   */
  override def applyScore(score: Score, joker: Joker): Score = 
    super.applyScore(joker.applySeven(score), joker)
}
