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
  
  def findById(id: Integer) = {decks.find(_.id == id)}
  
  var lillaPlusCards = List (Flashcard(1, "1+1 =", "2"),
		  					Flashcard(2, "1+2 =", "3"),
		  					Flashcard(3, "1+3 =", "4"),
		  					Flashcard(4, "1+4 =", "5"),
		  					Flashcard(5, "1+5 =", "6"),
		  					Flashcard(6, "1+6 =", "7"),
		  					Flashcard(7, "1+7 =", "8"),
		  					Flashcard(8, "1+8 =", "9"),
		  					Flashcard(9, "1+9 =", "10"),
		  					Flashcard(10, "2+1 =", "3"),
		  					Flashcard(11, "2+2 =", "4"),
		  					Flashcard(12, "2+3 =", "5"))
		  					
  var lillaMinusCards = List (Flashcard(4, "2-1=", "1"),
		  					Flashcard(5, "3-1=", "2"),
		  					Flashcard(6, "4-1=", "3"))
  
  var decks = Set(Deck(1, "Lilla plus", lillaPlusCards),
		  	  	Deck(2, "Lilla minus", lillaMinusCards)) 
		  	  
		  	  
}