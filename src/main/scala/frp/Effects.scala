package main.scala.frp

import scala.util.Try
import scala.util.Success
import scala.util.Failure

class Effects {
  // Deailing with failure explicitly
  
  // making failure evident in types
  trait Adventure {
    def collectCoins(): Try[List[Coin]]  // Or throws a Throwable
    def buyTreasure(coins: List[Coin]]: Try[Treasure]
  }
  
  val adventure = Adventure()
  val coins: Try[List[Coin]] = adventure.collectCoins()
  val treasure: Try[Treasure] = coins match {
    case Success(cs) => adventure.buyTreasure(cs)
    case failure@Failure(e) => failure
  }
  
  // flatMap, flatten, map, filter, recoverWith higher-order functions to manipulate Try[T]
  // Monads guide you through the happy path, Try[T], A monad that handles exceptions
  // Monad is type constructors that help you take the happy path and forget exception case
  
  // Noise reduction
  val treasure2: Try[Treasure] = 
    adventure.collectCoins().flatMap(coins => adventure.buyTreasure(coins))
    // flatMap is the plumber for the happy path
    
  // use comprehension syntax
  val treasure3: Try[Treasure] = for {
    coins <- adventure.collectCoins()
    treasure <- buyTreasure(coins)
  } yield treasure
}