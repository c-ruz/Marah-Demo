package model.rank.ranks

import model.base.Score
import model.joker.Joker
import model.rank.OddRank

/**
 * Represents the Three rank, classified as an odd */
object Three extends OddRank {

  /**
   * Returns the order of this rank. The order defines the specific sequencing
   * or position of the rank within a defined hierarchy or structure.
   *
   * @return the integer representing the order of this rank
   */
  override def order: Int = 3

  /**
   * Returns the value associated with this rank. The value typically represents
   * a numerical characteristic or property that differentiates this rank within
   * the hierarchy.
   *
   * @return the integer representing the value of this rank
   */
  override def value: Int = 3

  override def applyScore(score: Score, joker: Joker): Score = 
    super.applyScore(joker.applyThree(score), joker)
}
