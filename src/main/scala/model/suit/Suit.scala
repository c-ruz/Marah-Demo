package model.suit

import model.base.Score
import model.joker.Joker

/**
 * Represents a type of playing card suit. This trait contains methods
 * that define behavior associated with scoring logic, equality, and 
 * string representation specific to a card suit.
 */
trait Suit {
  
  /**
   * Modifies the provided `Score` object based on the current suit's logic
   * and additional transformations influenced by the provided `Joker`.
   *
   * @param score the initial `Score` object to be updated
   * @param joker the `Joker` that may influence the scoring logic
   * @return the updated `Score` object after applying the suit's transformation
   */
  def applyScore(score: Score, joker: Joker): Score
  
  /**
   * Compares this instance with the specified object for equality.
   *
   * The equality is determined based on the runtime class of the instance,
   * ensuring that only instances of the same class are considered equal.
   *
   * @param obj the object to compare this instance against
   * @return true if the specified object is equal to this instance, false otherwise
   */
  override def equals(obj: Any): Boolean = obj match {
    case that: Suit => this.getClass == that.getClass
    case _ => false
  }

  /**
   * Computes the hash code for the current instance.
   *
   * The hash code is derived from the runtime class of the instance, ensuring
   * consistency with the `equals` method, where instances of different runtime
   * classes are considered unequal.
   *
   * @return an integer representing the hash code of this instance
   */
  override def hashCode(): Int = getClass.hashCode()
}
