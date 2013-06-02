package models
import scala.util.Random

case class Deck(id: Int, name: String, flashcards: List[Flashcard]) {

  def size = flashcards.size
  
  def getFlashcardByIndex(flashCardIndex: Int): Flashcard =  flashcards(flashCardIndex)     
}

object Deck { 

  def findAll = decks.toList
  
  def findById(id: Integer) = {decks.find(_.id == id)}
  
  var lillaPlusCards = List (Flashcard(1, "1+1=", "2"),
		  					Flashcard(2, "1+2=", "3"),
		  					Flashcard(3, "1+3=", "4"))
		  					
  var lillaMinusCards = List (Flashcard(4, "2-1=", "1"),
		  					Flashcard(5, "3-1=", "2"),
		  					Flashcard(6, "4-1=", "3"))
  
  var decks = Set(Deck(1, "Lilla plus", lillaPlusCards),
		  	  	Deck(2, "Lilla minus", lillaMinusCards)) 
		  	  
		  	  
}