package main.scala

object FPGuy {
  def main(args: Array[String]) = {
    args.map(_.toUpperCase).foreach {printf("%s ", _)} 
  }
}