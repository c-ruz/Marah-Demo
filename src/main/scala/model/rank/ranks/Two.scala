package model.rank.ranks

import model.base.Score
import model.joker.Joker
import model.rank.EvenRank

/**
 * Represents the Two rank, categorized as an even rank in the hierarchy of ranks.
 *
 * The Two rank has a predefined order and value, distinguishing it within 
 * the rank hierarchy. This class extends the `EvenRank` abstract base class,
 * adhering to the defined behavior and properties shared by all even ranks.
 *
 * @note The Two rank is part of the structured hierarchy of ranks and 
 *       shares characteristics with other even ranks.
 */
object Two extends EvenRank {

  /**
   * Returns the order of this rank. The order defines the specific sequencing
   * or position of the rank within a defined hierarchy or structure.
   *
   * @return the integer representing the order of this rank
   */
  override def order: Int = 2

  /**
   * Returns the value associated with this rank. The value typically represents
   * a numerical characteristic or property that differentiates this rank within
   * the hierarchy.
   *
   * @return the integer representing the value of this rank
   */
  override def value: Int = 2

  /**
   * Applies the scoring logic specific to the Two rank. This method first modifies 
   * the given Score object using the `joker`'s `applyTwo` method and then invokes 
   * the superclass's scoring logic with the updated score.
   *
   * @param score the initial Score object to be transformed
   * @param joker the Joker instance providing Two-specific scoring logic
   * @return the updated Score object after applying Two-specific and superclass scoring logic
   */
  override def applyScore(score: Score, joker: Joker): Score = 
    super.applyScore(joker.applyTwo(score), joker)
}
