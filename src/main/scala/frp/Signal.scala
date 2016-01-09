package main.scala.frp

import scala.util.DynamicVariable

/**
 * Discrete signals changed by events
 * ( FRP also treats continuous signals, values in these systems are often 
 * 		computed by sampling with sufficient intensity instead of event propagation)
 */
class StackableVariable[T](init: T) {
  private var values: List[T] = List(init)
  
  def value: T = values.head 
  
  def withValue[R](newValue: T)(op: => R): R = {
    values = newValue :: values 
    try op finally values = values.tail
  }
}

// Act as a sentinel object shows that initially there is no caller
object NoSignal extends Signal[Nothing](???) {
  
  /**
   * Disable computeValue for NoSignal
   * Since we cannot evaluate an expression of type Nothing
   */
  override def computeValue() = ()
}

object Signal {
  
  // Make ue of the worst kind of state: global state
  // What if we try to evaluate several signal expressions in parallel => data race
  // 
  // Fix: Use scala.util.DynamicVariable as Thread local state
  // The API fo DynamicVariable matches the one of StackableVariable
  //private val caller = new StackableVariable[Signal[_]](NoSignal)
  //
  // Issues of TLS
  //  Imperative nature often produces hidden dependencies which are hard to manage
  //  Its implementation on the JDK involes a global hash table looku
  //    which can be a performance problem
  //  It doesn't play well is situation where threads are multiplexed between several tasks
  private val caller = new DynamicVariable[Signal[_]](NoSignal)
  def apply[T](expr: => T) = new Signal(expr)
}

class Signal[T](expr: => T) {
  import Signal._
  private var myExpr: () => T = _
  private var myValue: T = _
  private var observers: Set[Signal[_]] = Set()
  update(expr)
  
  def apply() = {
    observers += caller.value 
    
    // S() = S() + 1 is a cyclic signal definition
    assert(!caller.value.observers.contains(this), "cyclic signal definition")
    myValue
  }
  
  protected def update(expr: => T): Unit = {
    myExpr = () => expr 
    computeValue()
  }
  
  protected def computeValue(): Unit = {
    val newValue = caller.withValue(this)(myExpr())
    if (myValue != newValue) {
      myValue = newValue
      val obs = observers
      observers = Set()
      obs.foreach(_.computeValue())
    }
  }
}


/**
 * Val is a Signal which can be updated by the client
 */
class Var[T](expr: => T) extends Signal[T](expr) {
  
  // Just expose the functionality from super
  override def update(expr: => T): Unit = super.update(expr)
}

object Var {
  def apply[T](expr: => T) = new Var(expr)
}