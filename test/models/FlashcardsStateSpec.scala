package models

import org.specs2.mutable._
class FlashcardsStateSpec extends Specification {
  
  // Exempel på 
  // https://gist.github.com/seratch/1414177

  val cards = List (Flashcard(0, "1+1=", "2"), Flashcard(1, "1+2=", "3"),Flashcard(2, "1+3=", "4"))
  val deck = Deck(1, "Lilla plus", Addition(), "Description", cards)
  
  "getRandomUnshownIndex()" should { 
       
    "throw if invalid state" in {
      FlashcardsState("").getRandomUnshownIndex() must throwA[Exception] 
      FlashcardsState("1").getRandomUnshownIndex() must throwA[Exception] 
          	
    } 
    
    "work with all cards unshown" in {
		for (i <- 0 until 20) {
			val index = FlashcardsState("000").getRandomUnshownIndex()
			index must beCloseTo(1, 1)
		}
     }
    
    "work with one shown card" in {
    	for (i <- 0 until 20) {
    		val index = FlashcardsState("100").getRandomUnshownIndex()
    	    index must beCloseTo(1, 1)
			index must not be equalTo(0)
			
			val index2 = FlashcardsState("010").getRandomUnshownIndex()
			index2 must beCloseTo(1, 1)
			index2 must not be equalTo(1)
			
			val index3 = FlashcardsState("001").getRandomUnshownIndex()
			index3 must beCloseTo(1, 1)
			index3 must not be equalTo(2)
		}
    }
    
        "work with all but one shown card" in {
    	for (i <- 0 until 20) {
			val index1 = FlashcardsState("011").getRandomUnshownIndex()
			index1 must be equalTo(0)
			
			val index2 = FlashcardsState("101").getRandomUnshownIndex()
			index2 must be equalTo(1)
			
			val index3 = FlashcardsState("110").getRandomUnshownIndex()
			index3 must be equalTo(2)
		}
    }
    
  }

}