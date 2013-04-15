package models

case class Deck(id: Integer, name: String, flashcards: Set[Flashcard]) {
  def size = flashcards.size
}

object Deck {
  
  def findAll = decks.toList
  
  def findById(id: Integer) = {decks.find(_.id == id)}
  
  var lillaPlusCards = Set (Flashcard(1, "1+1=", "2"),
		  					Flashcard(2, "1+2=", "3"),
		  					Flashcard(3, "1+3=", "4"))
		  					
  var lillaMinusCards = Set (Flashcard(4, "2-1=", "1"),
		  					Flashcard(5, "3-1=", "2"),
		  					Flashcard(6, "4-1=", "3"))
  
  var decks = Set(Deck(1, "Lilla plus", lillaPlusCards),
		  	  	Deck(2, "Lilla minus", lillaMinusCards)) 
		  	  
		  	  
}