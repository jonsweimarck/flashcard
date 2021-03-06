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
  
   private val lillaPlusCards = {
    var id = -1
    for{i <- 0 to 10;
    	j <- 0 to 10 if i + j <= 10 // || (i == 5 && j == 6) || (i == 6  && j == 5)) Uppsavjaskolan special struntar jag i
    } yield { 
      id = id + 1
      Flashcard(id, i + " + " + j + " = ", i + " + " + j + " = " + (i + j).toString)
    }
  }
  
  val lillaPlusCardsRandomMissing = {
    var id = -1
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
  
  private val lillaMinusCards = {
    var id = -1
    for{i <- 0 to 10;
    	j <- 0 to 10 if (i - j >= 0)
    } yield { 
      id = id + 1;
      Flashcard(id, i + " - " + j + " = ", i + " - " + j + " = " + (i - j).toString)
    }
  }
  
  private val lillaMinusCardsRandomMissing = {
    var id = -1
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
      var id = -1
      for(j <- 0 to 20 if x + j < 20) yield { 
        id = id + 1;
        rand.nextInt(2) match {
      	  case(0) => Flashcard(id, x + " + _ = " + (x + j).toString, x + " + " + j + " = " + (x + j).toString)
      	  case(1) => Flashcard(id, x + " + " + j + " = _", x +" + " + j + " = " + (x + j).toString)
        }
      }
    }
  
  def storaMinusFrom11to19DifferenceIs(difference: Int) = {
	  var id = -1
      for(j <- 11 to 19) yield { 
        id = id + 1;
        val otherTerm = Math.abs(difference -j)
        val answer = j + " - " + otherTerm.toString + " = " + difference
        rand.nextInt(3) match {
      	  case(0) => Flashcard(id, j + " - _ = " + difference, answer)
      	  case(1) => Flashcard(id, "_ - " + otherTerm + " = " + difference, answer)
      	  case(2) => Flashcard(id, j + " - " + otherTerm + " = _", answer)
        }
      }
    }

  def storaMinusFrom11To19_LastTermIs(lastTerm: Int) = {
    var id = -1
    for(j <- 11 to 19) yield {
      id = id + 1;
      val difference = Math.abs(j - lastTerm)
      val answerString = j + " - " + lastTerm.toString + " = " + difference
      rand.nextInt(3) match {
        case(0) => Flashcard(id, j + " - _ = " + difference, answerString)
        case(1) => Flashcard(id, "_ - " + lastTerm + " = " + difference, answerString)
        case(2) => Flashcard(id, j + " - " + lastTerm + " = _", answerString)
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
 private val storaPlusOnlyStartsWith8_9_10 = {
   var id = -1
   for {
     card <-   storaPlusOnlyStartsWithX(8)  ++ storaPlusOnlyStartsWithX(9) ++ storaPlusOnlyStartsWithX(10)
   } yield {
     id = id + 1
     Flashcard(id, card.question, card.answer)
   }
 }

  private val storaMinusFrom11to19DifferenceIs10or9 = {
    var id = -1
    for {
      card <- storaMinusFrom11to19DifferenceIs(9) ++ storaMinusFrom11to19DifferenceIs(10)
    } yield {
      id = id + 1
      Flashcard(id, card.question, card.answer)
    }
  }

  private val storaMinusFrom11To19_LastTermIs10or9 = {
      var id = -1
      for {
        card <- storaMinusFrom11To19_LastTermIs(10) ++ storaMinusFrom11To19_LastTermIs(9)
      } yield {
        id = id + 1
        Flashcard(id, card.question, card.answer)
      }
   }

  private val storaPlusCardsExcludingLillaPlus = {
    var id = -1
    for{i <- 0 to 20;
    	j <- 0 to 20 if (i + j > 10 ) && (i + j <= 20 )
    } yield { 
      id = id + 1;
      Flashcard(id, i + " + " + j + " = ", i + " + " + j + " = " + (i + j).toString)
    }
  }
  
  
  private val storaPlusCards = {
    var id = -1
    for { 
    	card <- lillaPlusCards ++ storaPlusCardsExcludingLillaPlus
    } yield {  
    	id = id + 1
    	Flashcard(id, card.question, card.answer)
    }
  }
  
  private val storaMinusCardsExcludingLillaMinus = {
    var id = -1
    for{i <- 0 to 20;
    	j <- 0 to 20 if (i - j >= 0) && (i > 10 || j > 10)
    } yield { 
      id = id + 1
      Flashcard(id, i + " - " + j + " = ", i + " - " + j + " = " + (i - j).toString)
    }
  }
  
  private val storaMinusCards = {
    var id = -1
    for { 
    	card <- lillaMinusCards ++ storaMinusCardsExcludingLillaMinus
    } yield {  
    	id = id + 1
    	Flashcard(id, card.question, card.answer)
    }
  }

  private val storaMinus_minusHalften_minusNastanHalften = List(
    Flashcard(0, "12 - _ = 6", "12 - 6 = 6"),
    Flashcard(1, "_ - 7 = 7", "14 - 7 = 7"),
    Flashcard(2, "16 - 8 = ", "16 - 8 = 8"),
    Flashcard(3, "18 - _ = 9", "18 - 9 = 9"),
    Flashcard(4, "_ - 10 = 10", "20 - 10 = 10"),
    Flashcard(5, "11 - _ = 6", "11 - 5 = 6"),
    Flashcard(6, "11 - 6 = ", "11 - 6 = 5"),
    Flashcard(7, "_ - 6 = 7", "13 - 6 = 7"),
    Flashcard(8, "13 - _ = 6", "13 - 7 = 6"),
    Flashcard(9, "15 - 7 = ", "15 - 7 = 8"),
    Flashcard(10, "_ - 8 = 7", "15 - 8 = 7")
  )

  private val storaMinus_helHand_nastanTiokamrater_jamnaTal = List(
    Flashcard(0, "12 - _ = 6", "12 - 5 = 7"),
    Flashcard(1, "11 - 3 = _", "11 - 3 = 8"),
    Flashcard(2, "_ - 4 = 8", "12 - 4 = 8"),
    Flashcard(3, "12 - _ = 5", "12 - 7 = 5"),
    Flashcard(4, "11 - 8 = _", "11 - 8 = 3"),
    Flashcard(5, "_ - 8 = 4", "12 - 8 = 4"),
    Flashcard(6, "13 - _ = 7", "13 - 5 = 7"),
    Flashcard(7, "11 - 4 = _", "11 - 4 = 7"),
    Flashcard(8, "_ - 6 = 8", "14 - 6 = 8"),
    Flashcard(9, "13 - _ = 5", "13 - 8 = 5"),
    Flashcard(10, "11 - 7 = _", "11 - 7 = 4"),
    Flashcard(11, "_ - 8 = 6", "14 - 8 = 6")
  )

  def createMultplicationTableFor(factor: Int) = {
   var id = -1
   for { factor2 <- 0 to 10} yield {
     id = id + 1
     Flashcard(id, factor + " * " + factor2 + " = ", factor + " * " + factor2 + " = " + (factor * factor2).toString)
   }
  }

  def compile(l1: List[Flashcard], l2: List[Flashcard]) = {
    var id = -1
    for(l <- l1 ++ l2) yield {
      id = id + 1
      Flashcard(id, l.question, l.answer)
    }
  }
 
  
  val decks = List(
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
		  	  	
		  	  	  Deck(17, "Stora plus (bara där första termen är 10, 9 eller 8)", Addition(), "Delmängd av stora plus. Första termen är alltid 8, 9 eller 10. Ibland saknas summan, ibland den andra termen. Exempelvis 10 + _ = 17", storaPlusOnlyStartsWith8_9_10.toList),

		  	  	  
		  	  	  Deck(30, "Stora minus (hela)", Subtraction(),"Subtraktion med två termer där ingen är högre än 20. Exempel 14 - 9 = 5 ", storaMinusCards.toList),
		  	  	  Deck(31, "Stora minus (förutom 'lilla minus')", Subtraction(), "Som stora minus, men utom de kort som ingår i lilla minus ", storaMinusCardsExcludingLillaMinus.toList),
		  	  	  Deck(32, "Nollans tabell", Multiplication(), "Exempelvis '0 * 5 = 0', '0 * 8 = 0'", createMultplicationTableFor(0).toList),
		  	  	  Deck(33, "Ettans tabell", Multiplication(), "Exempelvis '1 * 5 = 5', '1 * 8 = 8'", createMultplicationTableFor(1).toList),
		  	  	  Deck(34, "Tvåans tabell", Multiplication(), "Exempelvis '2 * 5 = 10', '2 * 8 = 16'", createMultplicationTableFor(2).toList),
		  	  	  Deck(35, "Treans tabell", Multiplication(), "Exempelvis '3 * 5 = 15', '3 * 8 = 24'", createMultplicationTableFor(3).toList),
		  	  	  Deck(36, "Fyrans tabell", Multiplication(), "Exempelvis '4 * 5 = 20', '4 * 8 = 32'", createMultplicationTableFor(4).toList),
		  	  	  Deck(37, "Femmans tabell", Multiplication(), "Exempelvis '5 * 5 = 25', '5 * 8 = 40'", createMultplicationTableFor(5).toList),
		  	  	  Deck(38, "Sexans tabell", Multiplication(), "Exempelvis '6 * 5 = 30', '6 * 8 = 48'", createMultplicationTableFor(6).toList),
		  	  	  Deck(39, "Sjuans tabell", Multiplication(), "Exempelvis '7 * 5 = 35', '7 * 8 = 56'", createMultplicationTableFor(7).toList),
		  	  	  Deck(40, "Åttans tabell", Multiplication(), "Exempelvis '8 * 5 = 40', '8 * 8 = 64'", createMultplicationTableFor(8).toList),
		  	  	  Deck(41, "Nians tabell", Multiplication(), "Exempelvis '9 * 5 = 45', '9 * 8 = 72'", createMultplicationTableFor(9).toList),
		  	  	  Deck(42, "Tians tabell", Multiplication(), "Exempelvis '10 * 5 = 50', '10 * 8 = 80'", createMultplicationTableFor(10).toList),
		  	  	  Deck(43, "Stora minus (endast 'Minus alla ental' och 'Minus alla enltal och ta ett från tiotalet')", Subtraction(), "Exempelvis '11 - 1 = _', '19 - _ = 9'", storaMinusFrom11to19DifferenceIs10or9.toList),
              Deck(44, "Stora minus (endast 'Minus 9' och 'Minus 10')", Subtraction(), "Exempelvis '17 - 9 = _', '12 - _ = 2'", storaMinusFrom11To19_LastTermIs10or9.toList),
              Deck(45, "Stora minus (endast 'Minus hälften' och 'Minus nästan hälften')", Subtraction(), "Exempelvis '12 - 6 = _', '13 - _ = 7'",  storaMinus_minusHalften_minusNastanHalften),
              Deck(46, "Stora minus (endast 'Minus hälften', 'Minus nästan hälften', 'Minus 9', 'Minus 10', Minus alla ental' och 'Minus alla ental och ta ett från tiotalet')", Subtraction(), "Exempelvis '12 - 6 = _', '13 - _ = 7'",
                compile(compile(storaMinus_minusHalften_minusNastanHalften, storaMinusFrom11To19_LastTermIs10or9.toList), storaMinusFrom11to19DifferenceIs10or9.toList)),
                Deck(47, "Stora minus (endast 'Hel hand', 'Nästan tiokamrater' och 'Jämna tal')", Subtraction(), "Exempelvis '12 - 5 = _', '13 - _ = 8'",  storaMinus_helHand_nastanTiokamrater_jamnaTal)


  ) 
		  	  
		  	  
}