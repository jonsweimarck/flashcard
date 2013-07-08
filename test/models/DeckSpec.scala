package models

import org.specs2.mutable._
class DeckSpec extends Specification {
  
  // Exempel på 
  // https://gist.github.com/seratch/1414177

  val cards = List (Flashcard(0, "1+1=", "2"), Flashcard(1, "1+2=", "3"),Flashcard(2, "1+3=", "4"))
  val deck = Deck(1, "Lilla plus", cards)
  
  "getFlashcardsByIndices()" should { 
      
    "return all cards" in {
      val cards = deck.getFlashcardsByIndices(List(0, 1, 2))
      cards.size must be equalTo 3
    } 
    
    "return no cards" in {
      val cards = deck.getFlashcardsByIndices(List())
      cards.size must be equalTo 0
    } 
    
    "return single card" in {
      val cards = deck.getFlashcardsByIndices(List(2))
      cards.size must be equalTo 1
    } 
  }

}