package main.scala.observer

/**
 * Observer Pattern:
 * 	The Goodness
 * 		Decoplues views from state
 * 		Allows to have a varying number of views of a given state
 * 		Simple to set up
 * 
 * 	Bad
 *		Forces imperative style, since handlers are Unit-typed
 * 		Many moving parts that need to be co-ordinated
 * 		Concurrent makes things more complicated	( what if one view subscribes 2 changing models?)
 * 		Views are still tighly bound to one state, view update happens immediately
 * 		Adobe 2008 shows 1/3 code is devoted to event handling of UI but found 1/2 bugs related
 * 
 * 	Future
 * 		Functional Reactive Programming
 */

trait Subscriber {
  def handle(publisher: Publisher): Unit
}

trait Publisher {
  private var subscribers: Set[Subscriber] = Set()
  
  def subscribe(subscriber: Subscriber): Unit = 
    subscribers += subscriber
    
  def unsubscribe(subscriber: Subscriber): Unit = 
    subscribers -= subscriber
    
  def publish(): Unit =
    subscribers.foreach(_.handle(this))
}


class BankAccount extends Publisher {
  private var balance = 0
  
  def currentBalance = balance 
  
  def deposit(amount: Int): Unit = 
    if (amount > 0) {
      balance = balance + amount
      publish()
    }
  
  def withdraw(amount: Int): Unit = 
    if (0 < amount && amount <= balance) {
      balance = balance - amount 
      publish()
    } else throw new Error("insufficient funds")
}


class Consolidator(observed: List[BankAccount]) extends Subscriber {
  observed.foreach(_.subscribe(this))
  
  private var total: Int = _  // _ leaves it uninitialized
  compute()
  
  private def compute() =
    total = observed.map(_.currentBalance).sum
    
  override def handle(publisher: Publisher): Unit = compute()
  
  def totalBalance = total
}
/**
val a = new BankAccount
val b = new BankAccount
val c = new Consolidator(List(a, b))
c.totalBalance
a deposit 200
c.totalBalance
a deposit 300
a withdraw 200
b deposit 300
c.totalBalance
 */
