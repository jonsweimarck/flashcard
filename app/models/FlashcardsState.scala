package models

import scala.util._
import models.FlashcardsState._
case class FlashcardsState (stateAsString: String) 
{
    // TODO Hur validera i konstruktorn? 
  // - stateAsString maste vara bara nollor och ettor
  
	val rand = new Random()
	
	val deckSize = stateAsString.length()
	
    def getRandomUnshownIndex(): Int = {
      def charToLookFor = {
        if (unshownIndexLeft) Unshown
        else NotOk
      }
    
	  if ((! unshownIndexLeft) && (! onlyRetriesLeft)) throw new Exception("Nothing to show!")
	  
	  val index = rand.nextInt(stateAsString.length)
	  if(stateAsString.charAt(index) == charToLookFor ) return index
	  else return getRandomUnshownIndex()
    }
	
  
    def allFinished = ! stateAsString.contains(Unshown) && ! stateAsString.contains(NotOk)
    
    def onlyRetriesLeft = (! stateAsString.contains(Unshown)) && stateAsString.contains(NotOk)
 
	def setShownOK(flashcardIndex: Integer): FlashcardsState = {
      if (stateAsString.charAt(flashcardIndex) == Unshown) setIndividualState(flashcardIndex, OkAfterFirstTry)
      else setIndividualState(flashcardIndex, OkAfterMultipleTries)
    }
	
    def setShownNOK(flashcardIndex: Integer): FlashcardsState = setIndividualState(flashcardIndex, NotOk) 
    
    def numberOfUnshownCards = stateAsString.count(_ == Unshown)
    def numberOfCardsOk = numberOfCardsOkAfterFirstTry + numberOfCardsOkAfterMultipleTries
    def numberOfCardsOkAfterFirstTry = stateAsString.count(_ == OkAfterFirstTry)
    def numberOfCardsOkAfterMultipleTries = stateAsString.count(_ == OkAfterMultipleTries)
    def numberOfCardsNotOk = stateAsString.count(_ == NotOk)
    
    private def setIndividualState(flashcardIndex: Integer, state: Char): FlashcardsState =
    	FlashcardsState(stateAsString.substring(0, flashcardIndex ) + state + stateAsString.substring(flashcardIndex +1))
	
    private def validFor(deckSize: Integer) = stateAsString.length == deckSize
	
	private def unshownIndexLeft = stateAsString.contains(Unshown)


}

object FlashcardsState {
  val Unshown = '0'
  val OkAfterFirstTry = '1'
  val NotOk = '2'
  val OkAfterMultipleTries = '3'
  
   def newInitialState(decksize: Integer) = FlashcardsState (Unshown.toString *  decksize)
}
	