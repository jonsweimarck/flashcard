import models.{Flashcard, Deck}

/**
 * Created by jons on 21/01/14.
 */

val deck = Deck.findById(43).get










deck.flashcards.foreach((f: Flashcard) => println(f))


















