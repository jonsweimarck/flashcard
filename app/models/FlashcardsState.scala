package models

import scala.util._

case class FlashcardsState (stateAsString: String) 
{
  val SingleFlashcardOK = "1"
  val SingleFlashcardNOK = "2"
  
  // TODO Hur validera i konstruktorn? 
  // - stateAsString måste vara bara nollor och ettor
  
	val rand = new Random()
	
	val deckSize = stateAsString.length()
	
    def getRandomUnshownIndex(): Int = {
      def charToLookFor = {
        if (unshownIndexLeft) '0'
        else '2'
      }
    
	  if (! validFor(deckSize)) throw new Exception("Flashcards and their state don't match")
	  if ((! unshownIndexLeft) && (! onlyRetriesLeft)) throw new Exception("Nothing to show!")
	  
	  val index = rand.nextInt(stateAsString.length)
	  if(stateAsString.charAt(index) == charToLookFor ) return index
	  else return getRandomUnshownIndex()
    }
	
  
    def allFinished = stateAsString == ("1" * deckSize)
    
    def onlyRetriesLeft = (! stateAsString.contains("0")) && stateAsString.contains("2")
  
	def validFor(deckSize: Integer) = stateAsString.length == deckSize
	
	def unshownIndexLeft = stateAsString.contains("0")
	
	def setShownOK(flashcardIndex: Integer): FlashcardsState = setShownState(flashcardIndex, SingleFlashcardOK)
	
    def setShownNOK(flashcardIndex: Integer): FlashcardsState = setShownState(flashcardIndex, SingleFlashcardNOK) 
    
    def numberOfUnshownCards = stateAsString.count(_ == '0')
    def numberOfCardsShownOK = stateAsString.count(_ == '1')
    def numberOfCardsShownNOK = stateAsString.count(_ == '2')
    
    private def setShownState(flashcardIndex: Integer, state: String): FlashcardsState = {
    	FlashcardsState(stateAsString.substring(0, flashcardIndex ) + state + stateAsString.substring(flashcardIndex +1))
	} 
}
object FlashcardsState {
    	def newInitialState(decksize: Integer) = FlashcardsState ("0" *  decksize)
}
	