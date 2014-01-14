package models

import models._
import scala.util.Random
object DeckWorksheet {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(106); 
 
 def add5(x: Int) = x +5;System.out.println("""add5: (x: Int)Int""");$skip(27); 
 
 val l = List(1,2,3,4,5);System.out.println("""l  : List[Int] = """ + $show(l ));$skip(51); 
 
 def f(x: Int) = if (x > 2) Some(x +1) else None;System.out.println("""f: (x: Int)Option[Int]""");$skip(20); val res$0 = 
 
 l.map(x => f(x));System.out.println("""res0: List[Option[Int]] = """ + $show(res$0));$skip(22); val res$1 = 
 l.flatMap(x => f(x));System.out.println("""res1: List[Int] = """ + $show(res$1));$skip(50); 
 
 val optHello: Option[String] = Option("Hello");System.out.println("""optHello  : Option[String] = """ + $show(optHello ));$skip(36); 
 val optNone: Option[String] = None;System.out.println("""optNone  : Option[String] = """ + $show(optNone ));$skip(31); val res$2 = 
 
 optHello.map(_.toUpperCase);System.out.println("""res2: Option[String] = """ + $show(res$2));$skip(28); val res$3 = 
 optNone.map(_.toUpperCase);System.out.println("""res3: Option[String] = """ + $show(res$3));$skip(41); val res$4 = 
 
 optHello.map(x => List(x) ++ List(x));System.out.println("""res4: Option[List[String]] = """ + $show(res$4))}

   
   }
