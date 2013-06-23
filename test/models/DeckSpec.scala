package models

import org.specs2.mutable._
class DeckSpec extends Specification {
  
  // Exempel på 
  // https://gist.github.com/seratch/1414177

  val cards = List (Flashcard(0, "1+1=", "2"), Flashcard(1, "1+2=", "3"),Flashcard(2, "1+3=", "4"))
  val deck = Deck(1, "Lilla plus", cards)
  
  "getRandomUnshownFlashcard(state: String)" should {
       
    "throw if invalid state" in {
    	deck.getRandomUnshownFlashcard("0") must throwA[Exception] 
    	deck.getRandomUnshownFlashcard("00") must throwA[Exception]
    	deck.getRandomUnshownFlashcard("0000") must throwA[Exception]
    	deck.getRandomUnshownFlashcard("1111") must throwA[Exception]    	
    } 
    
    "work with all cards unshown" in {
		for (i <- 0 until 20) {
			val card = deck.getRandomUnshownFlashcard("000")
			card.id must beCloseTo(1, 1)
		}
     }
    
    "work with one shown card" in {
    	for (i <- 0 until 20) {
			val card1 = deck.getRandomUnshownFlashcard("100")
			card1.id must beCloseTo(1, 1)
			card1.id must not be equalTo(0)
			
			val card2 = deck.getRandomUnshownFlashcard("010")
			card2.id must beCloseTo(1, 1)
			card2.id must not be equalTo(1)
			
			val card3 = deck.getRandomUnshownFlashcard("001")
			card3.id must beCloseTo(1, 1)
			card3.id must not be equalTo(2)
		}
    }
    
        "work with all but one shown card" in {
    	for (i <- 0 until 20) {
			val card1 = deck.getRandomUnshownFlashcard("011")
			card1.id must be equalTo(0)
			
			val card2 = deck.getRandomUnshownFlashcard("101")
			card2.id must be equalTo(1)
			
			val card3 = deck.getRandomUnshownFlashcard("110")
			card3.id must be equalTo(2)
		}
    }
    
  }

}