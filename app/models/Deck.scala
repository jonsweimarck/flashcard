package models
import scala.util.Random

abstract  class DeckType
case class Addition() extends DeckType
case class Subtraction() extends DeckType
case class Multiplication() extends DeckType
case class Division() extends DeckType

case class Deck(id: Int, name: String, deckType: DeckType, desc: String, flashcards: List[Flashcard]) {

  def size = flashcards.size
  
  def getFlashcardByIndex(flashCardIndex: Int): Flashcard =  flashcards(flashCardIndex) 
  
  def getFlashcardsByIndices(flashCardIndicies: List[Int]): List[Flashcard] = 
    flashcards.filter(f => flashCardIndicies.contains(f.id))
}

object Deck { 

  def findAll = decks.toList
  
  def findById(id: Integer) = decks.find(_.id == id)
  
  def findAdditionDecks() = findByDeckType(Addition())
  def findSubtractionDecks() = findByDeckType(Subtraction())
  def findMultiplicationDecks() = findByDeckType(Multiplication())
  def findDivisionDecks() = findByDeckType(Division())
  
  
  private def findByDeckType(deckType: DeckType) = Deck.decks.filter (d => d.deckType == deckType)
  
   private var lillaPlusCards = {
    var id = 0
    for{i <- 0 to 10;
    	j <- 0 to 10 if (i + j <= 10 )// || (i == 5 && j == 6) || (i == 6  && j == 5)) Uppsavjaskolan special struntar jag i
    } yield { 
      id = id + 1; 
      Flashcard(id, i + " + " + j + " = ", (i + j).toString)
    }
  }
  
  private var lillaMinusCards = {
    var id = 0
    for{i <- 0 to 10;
    	j <- 0 to 10 if (i - j >= 0)
    } yield { 
      id = id + 1; 
      Flashcard(id, i + " - " + j + " = ", (i - j).toString)
    }
  }
  
    private var storaPlusCardsExcludingLillaPlus = {
    var id = 0
    for{i <- 0 to 20;
    	j <- 0 to 20 if (i + j > 10 ) && (i + j <= 20 )
    } yield { 
      id = id + 1; 
      Flashcard(id, i + " + " + j + " = ", (i + j).toString)
    }
  }
  
  
  private var storaPlusCards = {
    var id = 0
    for { 
    	card <- lillaPlusCards ++ storaPlusCardsExcludingLillaPlus
    } yield {  
    	id = id + 1; 
    	Flashcard(id, card.question, card.answer)
    } 
  }
  
  private var storaMinusCardsExcludingLillaMinus = {
    var id = 0
    for{i <- 0 to 20;
    	j <- 0 to 20 if (i - j >= 0) && (i > 10 || j > 10)
    } yield { 
      id = id + 1; 
      Flashcard(id, i + " - " + j + " = ", (i - j).toString)
    }
  }
  
 private var storaMinusCards = {
    var id = 0
    for { 
    	card <- lillaMinusCards ++ storaMinusCardsExcludingLillaMinus
    } yield {  
    	id = id + 1; 
    	Flashcard(id, card.question, card.answer)
    } 
  }
  
 def createMultplicationTableFor(factor: Int) = {
   var id = 0
   for { factor2 <- 0 to 10} yield {
     id = id + 1
     Flashcard(id, factor + " * " + factor2 + " = ", (factor * factor2).toString)
   }
 }
 	  					
  var testCards = List (Flashcard(0, "1+0=", "1"),
		  					Flashcard(1, "2+0=", "2"),
		  					Flashcard(2, "3+0=", "3"))
  
  var decks = List(Deck(1, "Lilla plus", Addition(), "Addition med två termer vars summa inte överstiger 10. Exempelvis 3 + 5 = 8", lillaPlusCards.toList),
		  	  	  Deck(2, "Lilla minus", Subtraction(), "Subtraktion med två termer där ingen är högre än 10. Exempelvis 4 - 3 = 1", lillaMinusCards.toList),
		  	  	  Deck(3, "Stora plus (hela)", Addition(), "Addition med två termer vars summa inte överstiger 20. Exempel 8 + 9 = 17", storaPlusCards.toList),
		  	  	  Deck(4, "Stora plus (förutom 'lilla plus')", Addition(), "Som stora plus, men utom de kort som ingår i lilla plus", storaPlusCardsExcludingLillaPlus.toList),
		  	  	  Deck(5, "Stora minus (hela)", Subtraction(),"Subtraktion med två termer där ingen är högre än 20. Exempel 14 - 9 = 5", storaMinusCards.toList),
		  	  	  Deck(6, "Stora minus (förutom 'lilla minus')", Subtraction(), "Som stora minus, men utom de kort som ingår i lilla minus", storaMinusCardsExcludingLillaMinus.toList),
		  	  	  Deck(7, "Nollans tabell", Multiplication(), "Exempelvis '0 * 5 = 0', '0 * 8 = 0'", createMultplicationTableFor(0).toList),
		  	  	  Deck(8, "Ettans tabell", Multiplication(), "Exempelvis '1 * 5 = 5', '1 * 8 = 8'", createMultplicationTableFor(1).toList),
		  	  	  Deck(9, "Tvåans tabell", Multiplication(), "Exempelvis '2 * 5 = 10', '2 * 8 = 16'", createMultplicationTableFor(2).toList),
		  	  	  Deck(10, "Treans tabell", Multiplication(), "Exempelvis '3 * 5 = 15', '3 * 8 = 24'", createMultplicationTableFor(3).toList),
		  	  	  Deck(11, "Fyrans tabell", Multiplication(), "Exempelvis '4 * 5 = 20', '4 * 8 = 32'", createMultplicationTableFor(4).toList),
		  	  	  Deck(12, "Femmans tabell", Multiplication(), "Exempelvis '5 * 5 = 25', '5 * 8 = 40'", createMultplicationTableFor(5).toList),
		  	  	  Deck(13, "Sexans tabell", Multiplication(), "Exempelvis '6 * 5 = 30', '6 * 8 = 48'", createMultplicationTableFor(6).toList),
		  	  	  Deck(14, "Sjuans tabell", Multiplication(), "Exempelvis '7 * 5 = 35', '7 * 8 = 56'", createMultplicationTableFor(7).toList),
		  	  	  Deck(15, "Åttans tabell", Multiplication(), "Exempelvis '8 * 5 = 40', '8 * 8 = 64'", createMultplicationTableFor(8).toList),
		  	  	  Deck(16, "Nians tabell", Multiplication(), "Exempelvis '9 * 5 = 45', '9 * 8 = 72'", createMultplicationTableFor(9).toList),
		  	  	  Deck(17, "Tians tabell", Multiplication(), "Exempelvis '10 * 5 = 50', '10 * 8 = 80'", createMultplicationTableFor(10).toList)
		  	  	  //,Deck(5, "test", Addition(), "Testkortlek", testCards    )
		  	  	  ) 
		  	  
		  	  
}