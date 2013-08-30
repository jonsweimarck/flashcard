package controllers

import play.api._
import play.api.mvc._
import models._

import play.api.data.Form
import play.api.data.Forms.{mapping, number, nonEmptyText, boolean}

object Decks extends Controller {
  
	def details (id: Integer) = Action { implicit request =>
	  Deck.findById(id).map { deck => Ok(views.html.decks.deckdetails(deck)) }.getOrElse(NotFound)
	}
  
  	def list = Action { implicit request => 
	    Ok(views.html.decks.decklist(
	        Deck.findAdditionDecks(), 
	        Deck.findSubtractionDecks, 
	        Deck.findMultiplicationDecks, 
	        Deck.findDivisionDecks()))
	}
  	
  	def nextFlashcardFromState() = Action { implicit request =>
  	  stateForm.bindFromRequest().fold(
  	    formWithErrors => BadRequest("Couldn't Happen!"),
  	    stateParameters => {
  	      val deck: Deck = Deck.findById(stateParameters.deckId).getOrElse(throw new Exception("Can't find Deck with id " + stateParameters.deckId))
  	      val flashcardsState: FlashcardsState = FlashcardsState(stateParameters.flashcardsStateAsString)
  	      
  	      if(flashcardsState.allFinished) {
  	        val okAfterMultipleTries = deck.getFlashcardsByIndices(flashcardsState.getCardIndexesWith(FlashcardsState.OkAfterMultipleTries))
  	        Ok(views.html.decks.finished(deck.name, flashcardsState, okAfterMultipleTries))
  	      } else if(flashcardsState.onlyRetriesLeft && ! stateParameters.retriesHasStarted){
  	    	  Ok(views.html.flashcards.beforeretries(deck.id, flashcardsState))
  	      } else {
  	    	  val indexOfFlashCardToShow = flashcardsState.getRandomUnshownIndex()
			  val newOKState = flashcardsState.setShownOK(indexOfFlashCardToShow)
			  val newNOKState = flashcardsState.setShownNOK(indexOfFlashCardToShow)
	  	      val flashcard = deck.getFlashcardByIndex(indexOfFlashCardToShow)
	  		
	  	      Ok(views.html.flashcards.show(
	  	          deck.id, 
	  	          flashcard, 
	  	          flashcardsState, 
	  	          newOKState, 
	  	          newNOKState, 
	  	          flashcardsState.onlyRetriesLeft, 
	  	          stateParameters.retriesHasStarted))
  	      }
  	      
  	    }
  	  )
  	}

  	def initialFlashcard() = Action { implicit request =>
  	  initialFlashcardForm.bindFromRequest().fold(
  	    formWithErrors => BadRequest("Couldn't Happen!"),
  	    stateParameters => {
  	      val deck: Deck = Deck.findById(stateParameters.deckId).getOrElse(throw new Exception("Can't find Deck with id " + stateParameters.deckId))
  	      val flashcardsState = FlashcardsState.newInitialState(deck.size)
  	      
  	      if(flashcardsState.allFinished)  
  	        Ok(views.html.decks.deckdetails(deck))
  	      else {
  	    	  val indexOfFlashCardToShow = flashcardsState.getRandomUnshownIndex()
			  val newOKState = flashcardsState.setShownOK(indexOfFlashCardToShow)
			  val newNOKState = flashcardsState.setShownNOK(indexOfFlashCardToShow)
	  	      val flashcard = deck.getFlashcardByIndex(indexOfFlashCardToShow)
	  		
	  	      Ok(views.html.flashcards.show(deck.id, flashcard, flashcardsState, newOKState, newNOKState, flashcardsState.onlyRetriesLeft, false))
  	      }
  	      
  	    }
  	  )
  	}
  	 	
 	private val initialFlashcardForm: Form[InitialFlashcardParameters] = Form(
  			mapping(
  					"deckId" -> number
  					
  			)(InitialFlashcardParameters.apply)(InitialFlashcardParameters.unapply)
  	)
  	
   private val stateForm: Form[StateParameters] = Form(
  			mapping(
  					"deckId" -> number,
  					"flashcardsStateAsString" -> nonEmptyText,
  					"retriesHasStarted" -> boolean
  			)(StateParameters.apply)(StateParameters.unapply)
  	)
}