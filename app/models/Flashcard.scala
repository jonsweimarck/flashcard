package models

case class Flashcard (id: Int, question: String, answer: String){

}

object Flashcard {
  def findAll  = this.flashcards.toList 
  
  def findById(id: Int) = flashcards.find(_.id == id)
  
  def add(flashcards: Flashcard) {
	  this.flashcards = this.flashcards + flashcards
  }
  
  var flashcards = Set (Flashcard(1, "1+1=", "2"),
		  				Flashcard(2, "1+2=", "3"),
		  				Flashcard(3, "1+3=", "4"))
		  				
		  				
}