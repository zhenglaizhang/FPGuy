package org.zzl.fp.play

object Monads {
  println("Welcome to the Scala worksheet")
  
  
  List("abc", "def").map(_.toUpperCase)
  List("abc", "def").map(_.toUpperCase).flatten
  List("abc", "def").flatMap(_.toUpperCase)
  
  trait M[T] {
  	def flatMap[U](f: T => M[U]): M[U]
  }
  
  m map f == m flatMap (x=> unit(f(x)))
}