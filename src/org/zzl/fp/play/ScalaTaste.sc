package org.zzl.fp.play

object taste {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val x = 12                                      //> x  : Int = 12
  
  
  def hello = (s: String) => s.toUpperCase        //> hello: => String => String
	hello("hello")                            //> res0: String = HELLO
	
	scala.Console.println("verbose hello")    //> verbose hello
	
	
	case class Point(x: Double = 0.0, y: Double = 0.0)
	val p1 = Point(1)                         //> p1  : org.zzl.fp.play.taste.Point = Point(1.0,0.0)
	val p2 = p1.copy(x = 2.0)                 //> p2  : org.zzl.fp.play.taste.Point = Point(2.0,0.0)
	
	abstract class Shape() {
		def draw(offset: Point = Point(0.0, 0.0))(f:String => Unit): Unit = f(s"draw(offset=$offset), ${this.toString}")
	}
	
	import java.util.HashMap
	val map = new HashMap[String, Integer]()  //> map  : java.util.HashMap[String,Integer] = {}
	
	def double(i: Int) { 2 * i }              //> double: (i: Int)Unit
	double(12)
	
	"""First Line\n
	hello\tworld"""                           //> res1: String("First Line\\n\n\thello\\tworld") = First Line\n
                                                  //| 	hello\tworld
     
def hello2(name: String) = s"""Welcome!
  Hello, $name!
  * (Gratuitous Star!!)
  |We're glad you're here.
  |  Have some extra whitespace.""".stripMargin   //> hello2: (name: String)String
hello2("Programming Scala")                       //> res2: String = Welcome!
                                                  //|   Hello, Programming Scala!
                                                  //|   * (Gratuitous Star!!)
                                                  //| We're glad you're here.
                                                  //|   Have some extra whitespace.
                                                  
	var sym = 'symbol                         //> sym  : Symbol = 'symbol
	var sym2 = 'symbol                        //> sym2  : Symbol = 'symbol
	
	
	val tup = ("hello", 10L, 12.0F, (x: String) => Unit)
                                                  //> tup  : (String, Long, Float, String => Unit.type) = (hello,10,12.0,<function
                                                  //| 1>)
	tup._1                                    //> res3: String = hello
	tup._3                                    //> res4: Float = 12.0
	
	true.toString                             //> res5: String = true
	
	true toString                             //> res6: String = true
	
	def `test that addition works` = assert(1 + 1 == 2)
                                                  //> test that addition works: => Unit
                                                  
	def isEven(n: Int) = (n % 2) == 0         //> isEven: (n: Int)Boolean
	(1 to 10) filter isEven foreach println   //> 2
                                                  //| 4
                                                  //| 6
                                                  //| 8
                                                  //| 10
                                                  
	for (i <- 1 to 10
			if isEven(i)	// &&
			if i <= 8) {
		println(i)                        //> 2
                                                  //| 4
                                                  //| 6
                                                  //| 8
	}
	
	isEven{12}                                //> res7: Boolean = true
}