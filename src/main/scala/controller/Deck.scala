package controller

import model.base.Card
import model.rank.ranks.*
import model.suit.suits.*

import scala.util.Random

object Deck {
  private var cards = for
      rank <- List(Ace, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King)
      suit <- List(Clubs, Diamond, Spades, Hearts)
    yield new Card(rank, suit)
    
  cards = Random.shuffle(cards)
  
  def draw(n: Int): List[Card] = {
    val result = cards.take(n)
    cards = cards.drop(n)
    result
  }
}
