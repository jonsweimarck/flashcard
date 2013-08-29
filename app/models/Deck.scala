package models
import scala.util.Random

case class Deck(id: Int, name: String, desc: String, flashcards: List[Flashcard]) {

  def size = flashcards.size
  
  def getFlashcardByIndex(flashCardIndex: Int): Flashcard =  flashcards(flashCardIndex) 
  
  def getFlashcardsByIndices(flashCardIndicies: List[Int]): List[Flashcard] = 
    flashcards.filter(f => flashCardIndicies.contains(f.id))
}

object Deck { 

  def findAll = decks.toList
  
  def findById(id: Integer) = decks.find(_.id == id)
  
   private var lillaPlusCards = {
    var id = 0
    for{i <- 0 to 10;
    	j <- 0 to 10 if (i + j <= 10 )// || (i == 5 && j == 6) || (i == 6  && j == 5)) Uppsavjaskolan special struntar jag i
    } yield { 
      id = id + 1; 
      Flashcard(id, i + " + " + j + " = ", (i + j).toString)
    }
  }
  
  private var lillaMinusCards = {
    var id = 0
    for{i <- 0 to 10;
    	j <- 0 to 10 if (i - j >= 0)
    } yield { 
      id = id + 1; 
      Flashcard(id, i + " - " + j + " = ", (i - j).toString)
    }
  }
  
    private var storaPlusCardsExcludingLillaPlus = {
    var id = 0
    for{i <- 0 to 20;
    	j <- 0 to 20 if (i + j > 10 ) && (i + j <= 20 )
    } yield { 
      id = id + 1; 
      Flashcard(id, i + " + " + j + " = ", (i + j).toString)
    }
  }
  
  
  private var storaPlusCards = {
    var id = 0
    for { 
    	card <- lillaPlusCards ++ storaPlusCardsExcludingLillaPlus
    } yield {  
    	id = id + 1; 
    	Flashcard(id, card.question, card.answer)
    } 
  }
  
  private var storaMinusCardsExcludingLillaMinus = {
    var id = 0
    for{i <- 0 to 20;
    	j <- 0 to 20 if (i - j >= 0) && (i > 10 || j > 10)
    } yield { 
      id = id + 1; 
      Flashcard(id, i + " - " + j + " = ", (i - j).toString)
    }
  }
  
 private var storaMinusCards = {
    var id = 0
    for { 
    	card <- lillaMinusCards ++ storaMinusCardsExcludingLillaMinus
    } yield {  
    	id = id + 1; 
    	Flashcard(id, card.question, card.answer)
    } 
  }
  
 	  					
  var testCards = List (Flashcard(0, "1+0=", "1"),
		  					Flashcard(1, "2+0=", "2"),
		  					Flashcard(2, "3+0=", "3"))
  
  var decks = List(Deck(1, "Lilla plus", "Addition med två termer vars summa inte överstiger 10. Exempelvis 3 + 5 = 8", lillaPlusCards.toList),
		  	  	  Deck(2, "Lilla minus", "Subtraktion med två termer där ingen är högre än 10. Exempelvis 4 - 3 = 1", lillaMinusCards.toList),
		  	  	  Deck(3, "Stora plus (hela)", "Addition med två termer vars summa inte överstiger 20. Exempel 8 + 9 = 17", storaPlusCards.toList),
		  	  	  Deck(4, "Stora plus (förutom 'lilla plus')", "Som stora plus, men utom de kort som ingår i lilla plus", storaPlusCardsExcludingLillaPlus.toList),
		  	  	  Deck(5, "Stora minus (hela)", "Subtraktion med två termer där ingen är högre än 20. Exempel 14 - 9 = 5", storaMinusCards.toList),
		  	  	  Deck(6, "Stora minus (förutom 'lilla minus')", "Som stora minus, men utom de kort som ingår i lilla minus", storaMinusCardsExcludingLillaMinus.toList)
		  	  	  //,Deck(5, "test", "Testkortlek", testCards    )
		  	  	  ) 
		  	  
		  	  
}