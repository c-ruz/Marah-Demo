package model.rank.ranks

import model.base.Score
import model.joker.Joker
import model.rank.FaceRank

/**
 * Represents the King rank, which is one of the face ranks in a rank hierarchy.
 *
 * The King rank has a predefined order and value, positioning it near the top
 * of the rank hierarchy. It inherits functionality from the `FaceRank` class,
 * which provides common behavior and scoring logic for all face ranks.
 *
 * The King rank specifically defines its order and value but relies on the
 * overarching behavior defined by its parent class. It is equal to another
 * King instance but not to instances of other ranks or types.
 */
object King extends FaceRank {

  /**
   * Returns the order of this rank. The order defines the specific sequencing
   * or position of the rank within a defined hierarchy or structure.
   *
   * @return the integer representing the order of this rank
   */
  override def order: Int = 13

  /**
   * Returns the value associated with this rank. The value typically represents
   * a numerical characteristic or property that differentiates this rank within
   * the hierarchy.
   *
   * @return the integer representing the value of this rank
   */
  override def value: Int = 10

  /**
   * Applies the specific scoring logic for the King rank by utilizing the `Joker`
   * instance's `applyKing` method before invoking the superclass logic.
   *
   * This method modifies the given `Score` object by first applying the King-specific 
   * scoring transformation provided by the `Joker` instance and then further processes 
   * the modified score using the superclass's scoring logic.
   *
   * @param score the initial score to be transformed
   * @param joker the `Joker` instance providing King-specific scoring logic
   * @return the updated `Score` object after applying King-specific and superclass scoring logic
   */
  override def applyScore(score: Score, joker: Joker): Score = 
    super.applyScore(joker.applyKing(score), joker)
}
