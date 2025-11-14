package model.hand

import model.base.{Card, Score}
import model.rank.Rank
import model.suit.Suit

import scala.collection.mutable

abstract class APokerHand(val score: Score) extends PokerHand {
  protected val ranksTracker: mutable.Map[Rank, Int] = mutable.Map()
  protected val suitsTracker: mutable.Map[Suit, Int] = mutable.Map()

  override def scoringCards(cards: List[Card]): List[Card] = cards
}
