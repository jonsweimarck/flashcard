package models

//import models._
import scala.util.Random
object DeckWorksheet {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(507); 
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
	val rand = new Random();System.out.println("""rand  : scala.util.Random = """ + $show(rand ));$skip(104); 
	val flashcards = List (Flashcard(0, "1+1=", "2"), Flashcard(1, "1+2=", "3"),Flashcard(2, "1+3=", "4"));System.out.println("""flashcards  : List[models.Flashcard] = """ + $show(flashcards ));$skip(543); 

  def getRandomUnshownFlashcard(flashcardsState: String): Flashcard = {
   
    def getRandomUnshownIndex(maxIndexExclusive: Integer): Integer = {
	    val newId = rand.nextInt(maxIndexExclusive)
	    if(flashcardsState.charAt(newId) == '0') return newId
	    else return getRandomUnshownIndex(maxIndexExclusive)
    }
    
    if (flashcardsState.length != flashcards.length) throw new Exception("flashcardsState.length != flashcards.length")
    val flashIndex = getRandomUnshownIndex(flashcardsState.length)
    flashcards(flashIndex)
  };System.out.println("""getRandomUnshownFlashcard: (flashcardsState: String)models.Flashcard""");$skip(36); val res$0 = 
 
 getRandomUnshownFlashcard("000");System.out.println("""res0: models.Flashcard = """ + $show(res$0));$skip(34); val res$1 = 
 getRandomUnshownFlashcard("101");System.out.println("""res1: models.Flashcard = """ + $show(res$1));$skip(26); 
 
  
  val s = "0123012";System.out.println("""s  : String = """ + $show(s ));$skip(19); 
  val l = s.toList;System.out.println("""l  : List[Char] = """ + $show(l ));$skip(25); 
  val z = l.zipWithIndex;System.out.println("""z  : List[(Char, Int)] = """ + $show(z ));$skip(51); val res$2 = 
  
 for{zi <- z
   if(zi._1 == '1')
 } yield zi._2;System.out.println("""res2: List[Int] = """ + $show(res$2))}
   
   
  
  
}
