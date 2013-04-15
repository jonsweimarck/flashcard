package controllers

import play.api._
import play.api.mvc._
import models.Deck

object Decks extends Controller {
  
	def details (id: Integer) = Action { implicit request =>
	  Deck.findById(id).map { deck => Ok(views.html.decks.deckdetails(deck)) }.getOrElse(NotFound)
	}
  
  	def list = Action { implicit request => 
	    Ok(views.html.decks.decklist(Deck.findAll))
	}
  
}