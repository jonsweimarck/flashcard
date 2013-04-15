package controllers

import play.api._
import play.api.mvc._

import play.api.data.Form
import play.api.data.Forms.{mapping, number, nonEmptyText}
import play.api.i18n.Messages

import models.Flashcard

object Flashcards extends Controller {
  
	def details (id: Int) = Action { implicit request =>
	  Flashcard.findById(id).map { flashcard => Ok(views.html.flashcards.details(flashcard)) }.getOrElse(NotFound)
	}
  
  	def list = Action { implicit request => 
  	  	val flashcards = Flashcard.findAll
	    Ok(views.html.flashcards.list(flashcards))
	}
  	
  	def save = Action { implicit request =>
		val newFlashcardForm = this.flashcardForm.bindFromRequest()
		
		newFlashcardForm.fold(
				hasErrors = { form =>
					Redirect(routes.Flashcards.newFlashcard()).
						flashing(Flash(form.data) + ("error" -> Messages("validation.errors")))
				},
				success = { newFlashcard =>
					Flashcard.add(newFlashcard)
					val message = Messages("flashcard.new.success", newFlashcard.id)
					Redirect(routes.Flashcards.details(newFlashcard.id)).
						flashing("success" -> message)
				}
		)
	}
  	
  	def newFlashcard = Action { implicit request =>
		val form = 
		  if (flash.get("error").isDefined) this.flashcardForm.bind(flash.data)
		  else this.flashcardForm
		Ok(views.html.flashcards.editFlashcard(form))
	}
  	
  	private val flashcardForm: Form[Flashcard] = Form(
  			mapping(
  					"id" -> number.verifying( "validation.ean.duplicate", Flashcard.findById(_).isEmpty),
  					"question" -> nonEmptyText,
  					"answer" -> nonEmptyText
  			)(Flashcard.apply)(Flashcard.unapply)
  	)
  
}