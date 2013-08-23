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
    	j <- 0 to 10 if (i + j <= 10 )// || (i == 5 && j == 6) || (i == 6  && j == 5)) Uppsavjaskolan special struntar vi i
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
	  					
  var testCards = List (Flashcard(0, "1+0=", "1"),
		  					Flashcard(1, "2+0=", "2"),
		  					Flashcard(2, "3+0=", "3"))
  
  var decks = Set(Deck(1, "Lilla plus", "Addition med två termer vars summa inte överstiger 10. Exempelvis 3 + 5 = 8", lillaPlusCards.toList),
		  	  	  Deck(2, "Lilla minus", "Subtraktion med två termer där ingen är högre än 10. Exempelvis 4 - 3 = 1 ", lillaMinusCards.toList)
		  	  	 //,Deck(3, "test", "Testkortlek", testCards    )
		  	  	  ) 
		  	  
		  	  
}