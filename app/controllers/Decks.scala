package controllers

import play.api._
import play.api.mvc._
import models._

import play.api.data.Form
import play.api.data.Forms.{mapping, number, nonEmptyText}

object Decks extends Controller {
  
	def details (id: Integer) = Action { implicit request =>
	  Deck.findById(id).map { deck => Ok(views.html.decks.deckdetails(deck)) }.getOrElse(NotFound)
	}
  
  	def list = Action { implicit request => 
	    Ok(views.html.decks.decklist(Deck.findAll))
	}
  	
  	
  	def nextFlashcardFromState(deckId: Int, state: String) = {
  	  val deck: Deck = Deck.findById(deckId).getOrElse(throw new Exception("Can't find Deck with id " + deckId))
  	  val flashcardsState: FlashcardsState = FlashcardsState(state)
  	  
  	  flashcardFromState(deck, flashcardsState)
  	}
  	
  	def nextFlashcardFromState2() = Action { implicit request =>
  	  stateForm.bindFromRequest().fold(
  	    formWithErrors => BadRequest("Couldn't Happen!"),
  	    stateParameters => {
  	      val deck: Deck = Deck.findById(stateParameters.deckId).getOrElse(throw new Exception("Can't find Deck with id " + stateParameters.deckId))
  	      val flashcardsState: FlashcardsState = FlashcardsState(stateParameters.flashcardsStateAsString)
  	      
  	      if(flashcardsState.allFinished) {
  	        val okAfterMultipleTries = deck.getFlashcardsByIndices(flashcardsState.getCardIndexesWith(FlashcardsState.OkAfterMultipleTries))
  	        Ok(views.html.decks.finished(deck.name, flashcardsState, okAfterMultipleTries))
  	      } else {
  	    	  val indexOfFlashCardToShow = flashcardsState.getRandomUnshownIndex()
			  val newOKState = flashcardsState.setShownOK(indexOfFlashCardToShow)
			  val newNOKState = flashcardsState.setShownNOK(indexOfFlashCardToShow)
	  	      val flashcard = deck.getFlashcardByIndex(indexOfFlashCardToShow)
	  		
	  	      Ok(views.html.flashcards.show(deck.id, flashcard, flashcardsState, newOKState, newNOKState, flashcardsState.onlyRetriesLeft))
  	      }
  	      
  	    }
  	  )
  	}

  	def initialFlashcard2() = Action { implicit request =>
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
	  		
	  	      Ok(views.html.flashcards.show(deck.id, flashcard, flashcardsState, newOKState, newNOKState, flashcardsState.onlyRetriesLeft))
  	      }
  	      
  	    }
  	  )
  	}
  	
  	
  	def initialFlashcard(deckId: Int) = {
  	  val deck = Deck.findById(deckId).getOrElse(throw new Exception("Can't find Deck with id " + deckId))
  	  val flashcardState = FlashcardsState.newInitialState(deck.size)

  	  flashcardFromState(deck, flashcardState)
  	}
  	
  	private def flashcardFromState(deck: Deck, flashcardsState: FlashcardsState) =  Action { implicit request =>
  	  val indexOfFlashCardToShow = flashcardsState.getRandomUnshownIndex()
  	  val newOKState = flashcardsState.setShownOK(indexOfFlashCardToShow)
  	  val newNOKState = flashcardsState.setShownNOK(indexOfFlashCardToShow)
  	  val flashcard = deck.getFlashcardByIndex(indexOfFlashCardToShow)
  		
  	  Ok(views.html.flashcards.show(deck.id, flashcard, flashcardsState, newOKState, newNOKState, flashcardsState.onlyRetriesLeft))
  	}
  	
 	private val initialFlashcardForm: Form[InitialFlashcardParameters] = Form(
  			mapping(
  					"deckId" -> number
  					
  			)(InitialFlashcardParameters.apply)(InitialFlashcardParameters.unapply)
  	)
  	
   private val stateForm: Form[StateParameters] = Form(
  			mapping(
  					"deckId" -> number,
  					"flashcardsStateAsString" -> nonEmptyText
  			)(StateParameters.apply)(StateParameters.unapply)
  	)
  	
  
}