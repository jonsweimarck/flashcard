package models
import scala.util.Random

case class Deck(id: Int, name: String, flashcards: List[Flashcard]) {

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
    	j <- 0 to 10 if (i + j <= 10 || (i == 5 && j == 6) || (i == 6  && j == 5))
    } yield { 
      id = id + 1; 
      Flashcard(id, i + "+" + j + " =", (i + j).toString)
    }
  }
	  					
  var lillaMinusCards = List (Flashcard(1, "2-1=", "1"),
		  					Flashcard(2, "3-1=", "2"),
		  					Flashcard(3, "4-1=", "3"))
  
  var decks = Set(Deck(1, "Lilla plus", lillaPlusCards.toList),
		  	  	  Deck(2, "Lilla minus", lillaMinusCards)) 
		  	  
		  	  
}