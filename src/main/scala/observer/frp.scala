package main.scala.observer

/**
 * Reactive programming is about reacting to sequenes of events that happen in time
 * 
 * Functional view: Aggregate an event sequence into a signal
 * 	A signal is a value that changes over time
 * 	It is represented as a function from time to the value domain
 * 	Instead of propagating updates to mutable state, we define new signals in terms of existing ones
 * 
 * 
 * Event-based view
 * 	an MouseMoved(toPos: Position) event is fired
 * 
 * FRP view:
 * 	a signal mousePosition: Signal[Position] which at any point in time represents the current mouse position
 * 
 * 
 * Event straming dataflow programming systems such as Rx
 * 
 * frp.Signal - signal-send wires
 * Scala.React
 *
 */
object frp {
   /**
    * @author darinzh
    */
   class BankAccount() {
     val balance = Var(0)     
     def deposit(amount: Int) = 
       if (amount > 0) {
         balance() = balance() + amount
       }
     
     def withdraw(amount: Int) = {
       if (0 < amount && amount <= balance) {
         balance -= amount 
       } else throw new Error("insufficient funds")
     }
   }
   
   object accounts {
     
   }
}