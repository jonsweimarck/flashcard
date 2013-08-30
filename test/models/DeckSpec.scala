package models

import org.specs2.mutable._
class DeckSpec extends Specification {
  
  // Exempel på 
  // https://gist.github.com/seratch/1414177

  val testCards = List (Flashcard(0, "1+1=", "2"), Flashcard(1, "1+2=", "3"),Flashcard(2, "1+3=", "4"))
  val deck = Deck(1, "testDeck", Addition(), "Description", testCards)
  
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
  
  
  "findAdditionDecks()" should {
    
	  "return 3 decks" in {
	    val decks = Deck.findAdditionDecks()
	    decks.size must be equalTo 3
	  }
  }
  
  "findSubtractionDecks()" should {
    
	  "return 3 decks" in {
	    val decks = Deck.findSubtractionDecks()
	    decks.size must be equalTo 3
	  }
  }
  
  "decks() 'lilla plus'" should {
    
    "return 66 cards" in {
      Deck.findById(1).get.flashcards.size must be equalTo 66
    }

    // Uppsavjaskolan special med 5+6 och 6+5 struntar jag i
//    "return only two answers over 10" in {
//      val cards = Deck.findById(1).get.flashcards
//      val over10 = cards.filter(f => f.answer.toInt > 10)
//      over10.size must be equalTo 2
//      //cards.foreach(println _)
//    }
//    
//    "return 2 cards with answer '11'" in {
//      val cards = Deck.findById(1).get.flashcards
//      cards.filter(f => f.answer.toInt == 11).size must be equalTo 2
//    }
  }
  
   "decks() 'lilla minus'" should {
    
    "return 66 cards" in {
      Deck.findById(2).get.flashcards.size must be equalTo 66
    }
    
    "return 11 answers under 1" in {
      val cards = Deck.findById(2).get.flashcards
      val under1 = cards.filter(f => f.answer.toInt < 1)
      under1.size must be equalTo 11
     // cards.foreach(println _)
    }
    
    "return 11 card with answer '0'" in {
      val cards = Deck.findById(2).get.flashcards
      cards.filter(f => f.answer.toInt == 0).size must be equalTo 11
    }
   }
   
   "decks() 'stora plus ('hela')" should {
    
	    "return 231 cards" in {
	      Deck.findById(3).get.flashcards.size must be equalTo 231
	      //Deck.findById(3).get.flashcards.foreach(println _)
	    }
   } 

   "decks() 'stora plus ('forutom lilla plus')" should {
    
	    "return 165 cards" in {
	      Deck.findById(4).get.flashcards.size must be equalTo 165
	      //Deck.findById(4).get.flashcards.foreach(println _)
	    }
   } 
   
   "decks() 'stora minus('hela')" should {
    
	    "return 231 cards" in {
	      Deck.findById(5).get.flashcards.size must be equalTo 231
	      //Deck.findById(5).get.flashcards.foreach(println _)
	    }
   } 

   "decks() 'stora minus ('forutom lilla minus')" should {
    
	    "return 165 cards" in {
	      Deck.findById(6).get.flashcards.size must be equalTo 165
	      //Deck.findById(6).get.flashcards.foreach(println _)
	    }
   }
   
   
}