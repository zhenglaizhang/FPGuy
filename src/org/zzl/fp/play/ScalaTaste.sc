package org.zzl.fp.play

object taste {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val x = 12                                      //> x  : Int = 12
  
  
  def hello = (s: String) => s.toUpperCase        //> hello: => String => String
	hello("hello")                            //> res0: String = HELLO
	
	scala.Console.println("verbose hello")    //> verbose hello
	
}