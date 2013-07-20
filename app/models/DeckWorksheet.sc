package models

//import models._
import scala.util.Random
object DeckWorksheet {
 /*
  def newState(state: String, deckSize: Integer, flashcardId: Integer): String = {
    if(state == "0")
    	 ("0" * (flashcardId)) + "1" + ("0" * (deckSize - flashcardId - 1))
    else
      state.substring(0, flashcardId ) + "1" + state.substring(flashcardId +1)
  }
  
  newState("0", 5, 2)
  newState("0", 5, 0)
  newState("00000", 5, 2)
  newState("00000", 5, 0)
  newState("02020", 5, 2)
*/
	val rand = new Random()                   //> rand  : scala.util.Random = scala.util.Random@47315d34
	val flashcards = List (Flashcard(0, "1+1=", "2"), Flashcard(1, "1+2=", "3"),Flashcard(2, "1+3=", "4"))
                                                  //> flashcards  : List[models.Flashcard] = List(Flashcard(0,1+1=,2), Flashcard(1
                                                  //| ,1+2=,3), Flashcard(2,1+3=,4))

  def getRandomUnshownFlashcard(flashcardsState: String): Flashcard = {
   
    def getRandomUnshownIndex(maxIndexExclusive: Integer): Integer = {
	    val newId = rand.nextInt(maxIndexExclusive)
	    if(flashcardsState.charAt(newId) == '0') return newId
	    else return getRandomUnshownIndex(maxIndexExclusive)
    }
    
    if (flashcardsState.length != flashcards.length) throw new Exception("flashcardsState.length != flashcards.length")
    val flashIndex = getRandomUnshownIndex(flashcardsState.length)
    flashcards(flashIndex)
  }                                               //> getRandomUnshownFlashcard: (flashcardsState: String)models.Flashcard
 
 getRandomUnshownFlashcard("000")                 //> res0: models.Flashcard = Flashcard(1,1+2=,3)
 getRandomUnshownFlashcard("101")                 //> res1: models.Flashcard = Flashcard(1,1+2=,3)
 
  
  val s = "0123012"                               //> s  : String = 0123012
  val l = s.toList                                //> l  : List[Char] = List(0, 1, 2, 3, 0, 1, 2)
  val z = l.zipWithIndex                          //> z  : List[(Char, Int)] = List((0,0), (1,1), (2,2), (3,3), (0,4), (1,5), (2,
                                                  //| 6))
  
 for{zi <- z
   if(zi._1 == '1')
 } yield zi._2                                    //> res2: List[Int] = List(1, 5)
   
 val b = "true".toBoolean                         //> b  : Boolean = true
  
  
  
}