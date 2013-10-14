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
  
  val rand = new Random()
  
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
      Flashcard(id, i + " + " + j + " = ", i + " + " + j + " = " + (i + j).toString)
    }
  }
  
  var lillaPlusCardsRandomMissing = {
    var id = 0
    for{i <- 0 to 10;
    	j <- 0 to 10 if (i + j <= 10 )
    } yield {
      id = id + 1;
     
      rand.nextInt(2) match {
      	case(0) => Flashcard(id, (i + j).toString + " = _ + " + j, (i + j).toString + " = " + i + " + " + j)
      	case(1) => Flashcard(id,  (i + j).toString + " = " + i + " + _",  (i + j).toString + " = " + i + " + " + j)
      }     
    }
  }
  
  private var lillaMinusCards = {
    var id = 0
    for{i <- 0 to 10;
    	j <- 0 to 10 if (i - j >= 0)
    } yield { 
      id = id + 1; 
      Flashcard(id, i + " - " + j + " = ", i + " - " + j + " = " + (i - j).toString)
    }
  }
  
  private var lillaMinusCardsRandomMissing = {
    var id = 0
    for{i <- 0 to 10;
    	j <- 0 to 10 if (i - j >= 0)
    } yield { 
      id = id + 1; 
      rand.nextInt(2) match {
      	case(0) => Flashcard(id, (i - j).toString + " = _ - " + j , (i - j).toString + " = " + i + " - " + j)
      	case(1) => Flashcard(id, (i - j).toString + " = " + i + " - _" , (i - j).toString + " = " + i + " - " + j)
      } 
    }
  }
  
  def storaPlusOnlyStartsWithX(x: Int) = {
      var id = 0
      for(j <- 0 to 20 if x + j < 20) yield { 
        id = id + 1; 
        rand.nextInt(2) match {
      	  case(0) => Flashcard(id, x + " + _ = " + (x + j).toString, x + " + " + j + " = " + (x + j).toString)
      	  case(1) => Flashcard(id, x + " + " + j + " = _", x +" + " + j + " = " + (x + j).toString)
        } 
      } 
    }
    
 private val storaPlusOnlyStartsWith10 = storaPlusOnlyStartsWithX(10)
 private val storaPlusOnlyStartsWith9 = storaPlusOnlyStartsWithX(9)
 private val storaPlusOnlyStartsWith8 = storaPlusOnlyStartsWithX(8)
 private val storaPlusOnlyStartsWith7 = storaPlusOnlyStartsWithX(7)
 private val storaPlusOnlyStartsWith6 = storaPlusOnlyStartsWithX(6)
 private val storaPlusOnlyStartsWith5 = storaPlusOnlyStartsWithX(5)
 private val storaPlusOnlyStartsWith4 = storaPlusOnlyStartsWithX(4)
 private val storaPlusOnlyStartsWith3 = storaPlusOnlyStartsWithX(3)
 private val storaPlusOnlyStartsWith2 = storaPlusOnlyStartsWithX(2)
 private val storaPlusOnlyStartsWith1 = storaPlusOnlyStartsWithX(1)
 
 
  
  private val storaPlusCardsExcludingLillaPlus = {
    var id = 0
    for{i <- 0 to 20;
    	j <- 0 to 20 if (i + j > 10 ) && (i + j <= 20 )
    } yield { 
      id = id + 1; 
      Flashcard(id, i + " + " + j + " = ", i + " + " + j + " = " + (i + j).toString)
    }
  }
  
  
  private val storaPlusCards = {
    var id = 0
    for { 
    	card <- lillaPlusCards ++ storaPlusCardsExcludingLillaPlus
    } yield {  
    	id = id + 1; 
    	Flashcard(id, card.question, card.answer)
    } 
  }
  
  private val storaMinusCardsExcludingLillaMinus = {
    var id = 0
    for{i <- 0 to 20;
    	j <- 0 to 20 if (i - j >= 0) && (i > 10 || j > 10)
    } yield { 
      id = id + 1; 
      Flashcard(id, i + " - " + j + " = ", i + " - " + j + " = " + (i - j).toString)
    }
  }
  
  private val storaMinusCards = {
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
     Flashcard(id, factor + " * " + factor2 + " = ", factor + " * " + factor2 + " = " + (factor * factor2).toString)
   }
 }
 
  
  var decks = List(
		  		  Deck(1, "Lilla plus", Addition(), "Addition med två termer vars summa inte överstiger 10. Exempelvis 3 + 5 = 8 ", lillaPlusCards.toList),
		  		  Deck(2, "Lilla plus (med saknade termer)", Addition(), "Som vanliga lilla plus men en term saknas istället för summan. Exempelvis 8 = 3 + _ ", lillaPlusCardsRandomMissing.toList),
		  	  	  Deck(3, "Lilla minus", Subtraction(), "Subtraktion med två termer där ingen är högre än 10. Exempelvis 4 - 3 = 1 ", lillaMinusCards.toList),
		  	  	  Deck(4, "Lilla minus (med saknade termer)", Subtraction(), "Som vanliga lilla minus men en term saknas istället för differensen. Exempelvis 8 = 4 - _ ", lillaMinusCardsRandomMissing.toList),
		  	  	  
		  	  	  Deck(5, "Stora plus (hela)", Addition(), "Addition med två termer vars summa inte överstiger 20. Exempel 8 + 9 = 17", storaPlusCards.toList),
		  	  	  Deck(6, "Stora plus (förutom 'lilla plus')", Addition(), "Som stora plus, men utom de kort som ingår i lilla plus ", storaPlusCardsExcludingLillaPlus.toList),
		  	  	  Deck(7, "Stora plus (bara där första termen är 10)", Addition(), "Delmängd av stora plus. Första termen är alltid 10. Ibland saknas summan, ibland den andra termen. Exempelvis 10 + _ = 16", storaPlusOnlyStartsWith10.toList),
		  	  	  Deck(8, "Stora plus (bara där första termen är 9)", Addition(), "Delmängd av stora plus. Första termen är alltid 9. Ibland saknas summan, ibland den andra termen. Exempelvis 9 + _ = 17", storaPlusOnlyStartsWith9.toList),
		  	  	  Deck(9, "Stora plus (bara där första termen är 8)", Addition(), "Delmängd av stora plus. Första termen är alltid 8. Ibland saknas summan, ibland den andra termen. Exempelvis 8 + _ = 17", storaPlusOnlyStartsWith8.toList),
		  	  	  Deck(10, "Stora plus (bara där första termen är 7)", Addition(), "Delmängd av stora plus. Första termen är alltid 7. Ibland saknas summan, ibland den andra termen. Exempelvis 7 + _ = 17", storaPlusOnlyStartsWith7.toList),
		  	  	  Deck(11, "Stora plus (bara där första termen är 6)", Addition(), "Delmängd av stora plus. Första termen är alltid 6. Ibland saknas summan, ibland den andra termen. Exempelvis 6 + _ = 17", storaPlusOnlyStartsWith6.toList),
		  	  	  Deck(12, "Stora plus (bara där första termen är 5)", Addition(), "Delmängd av stora plus. Första termen är alltid 5. Ibland saknas summan, ibland den andra termen. Exempelvis 5 + _ = 17", storaPlusOnlyStartsWith5.toList),
		  	  	  Deck(13, "Stora plus (bara där första termen är 4)", Addition(), "Delmängd av stora plus. Första termen är alltid 4. Ibland saknas summan, ibland den andra termen. Exempelvis 4 + _ = 17", storaPlusOnlyStartsWith4.toList),
		  	  	  Deck(14, "Stora plus (bara där första termen är 3)", Addition(), "Delmängd av stora plus. Första termen är alltid 3. Ibland saknas summan, ibland den andra termen. Exempelvis 3 + _ = 17", storaPlusOnlyStartsWith3.toList),
		  	  	  Deck(15, "Stora plus (bara där första termen är 2)", Addition(), "Delmängd av stora plus. Första termen är alltid 2. Ibland saknas summan, ibland den andra termen. Exempelvis 2 + _ = 17", storaPlusOnlyStartsWith2.toList),
		  	  	  Deck(16, "Stora plus (bara där första termen är 1)", Addition(), "Delmängd av stora plus. Första termen är alltid 1. Ibland saknas summan, ibland den andra termen. Exempelvis 1 + _ = 17", storaPlusOnlyStartsWith1.toList),
		  	  	
		  	  	  Deck(17, "Stora minus (hela)", Subtraction(),"Subtraktion med två termer där ingen är högre än 20. Exempel 14 - 9 = 5 ", storaMinusCards.toList),
		  	  	  Deck(18, "Stora minus (förutom 'lilla minus')", Subtraction(), "Som stora minus, men utom de kort som ingår i lilla minus ", storaMinusCardsExcludingLillaMinus.toList),
		  	  	  Deck(19, "Nollans tabell", Multiplication(), "Exempelvis '0 * 5 = 0', '0 * 8 = 0'", createMultplicationTableFor(0).toList),
		  	  	  Deck(20, "Ettans tabell", Multiplication(), "Exempelvis '1 * 5 = 5', '1 * 8 = 8'", createMultplicationTableFor(1).toList),
		  	  	  Deck(21, "Tvåans tabell", Multiplication(), "Exempelvis '2 * 5 = 10', '2 * 8 = 16'", createMultplicationTableFor(2).toList),
		  	  	  Deck(22, "Treans tabell", Multiplication(), "Exempelvis '3 * 5 = 15', '3 * 8 = 24'", createMultplicationTableFor(3).toList),
		  	  	  Deck(23, "Fyrans tabell", Multiplication(), "Exempelvis '4 * 5 = 20', '4 * 8 = 32'", createMultplicationTableFor(4).toList),
		  	  	  Deck(24, "Femmans tabell", Multiplication(), "Exempelvis '5 * 5 = 25', '5 * 8 = 40'", createMultplicationTableFor(5).toList),
		  	  	  Deck(25, "Sexans tabell", Multiplication(), "Exempelvis '6 * 5 = 30', '6 * 8 = 48'", createMultplicationTableFor(6).toList),
		  	  	  Deck(26, "Sjuans tabell", Multiplication(), "Exempelvis '7 * 5 = 35', '7 * 8 = 56'", createMultplicationTableFor(7).toList),
		  	  	  Deck(27, "Åttans tabell", Multiplication(), "Exempelvis '8 * 5 = 40', '8 * 8 = 64'", createMultplicationTableFor(8).toList),
		  	  	  Deck(28, "Nians tabell", Multiplication(), "Exempelvis '9 * 5 = 45', '9 * 8 = 72'", createMultplicationTableFor(9).toList),
		  	  	  Deck(29, "Tians tabell", Multiplication(), "Exempelvis '10 * 5 = 50', '10 * 8 = 80'", createMultplicationTableFor(10).toList)
		  	  	  ) 
		  	  
		  	  
}