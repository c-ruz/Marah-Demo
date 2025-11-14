package model.base

import model.hand.PokerHand
import model.hand.hands.*
import model.joker.Joker

object Scorer {
  private val handOrder: List[PokerHand] = List(
    new StraightFlush(),
    new FourOfAKind(),
    new FullHouse(),
    new Flush(),
    new Straight(),
    new ThreeOfAKind(),
    new TwoPair(),
    new Pair(),
    new HighCard()
  )

  def score(cards: List[Card], jokers: List[Joker]): Score = {
    val handScored = handOrder.find(_.check(cards)).get

    val score = handScored.score

    val scoredCards = handScored.scoringCards(cards)

    val scoreCardsJokers = scoredCards.foldLeft(score)((acc, card) =>
      card.applyScore(acc, jokers)
    )

    val scorePokerHandJokers =
      jokers.foldLeft(scoreCardsJokers)((acc, joker) =>
        handScored.applyScore(acc, joker))

    val scoreFlatJokers =
      jokers.foldLeft(scorePokerHandJokers)((acc, joker) =>
        joker.applyScore(acc))

    scoreFlatJokers
  }
}
