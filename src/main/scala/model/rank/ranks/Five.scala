package model.rank.ranks

import model.base.Score
import model.joker.Joker
import model.rank.OddRank

/**
 * Represents the rank with the ordinal order and value of five, categorized under 
 * the hierarchy of odd ranks. This class is a concrete implementation of the 
 * `OddRank` abstract base class, defining a rank that is odd-valued and possesses */
object Five extends OddRank {

  /**
   * Returns the order of this rank. The order defines the specific sequencing
   * or position of the rank within a defined hierarchy or structure.
   *
   * @return the integer representing the order of this rank
   */
  override def order: Int = 5

  /**
   * Returns the value associated with this rank. The value typically represents
   * a numerical characteristic or property that differentiates this rank within
   * the hierarchy.
   *
   * @return the integer representing the value of this rank
   */
  override def value: Int = 5

  /**
   * Applies a scoring transformation specific to the rank "Five". This method utilizes
   * the `Joker` instance to apply the `applyFive` method to the initial score before
   * invoking the superclass's scoring logic.
   *
   * @param score the initial score to be transformed
   * @param joker the Joker instance providing Five-specific scoring logic
   * @return the updated Score object after applying the Five-specific and superclass scoring logic
   */
  override def applyScore(score: Score, joker: Joker): Score = 
    super.applyScore(joker.applyFive(score), joker)
}
