package org.zzl.fp.play

object FunctionalRandomGenerators {
  import java.util.Random
  val rand = new Random                           //> rand  : java.util.Random = java.util.Random@f2a0b8e
  rand.nextInt()                                  //> res0: Int = 1720263433
  
  
  
  trait Generator[+T] {
 		def generate: T
 	}
 	
 	val integers = new Generator[Int] {
 		val rand = new java.util.Random
 		def generate = rand.nextInt()
 	}                                         //> integers  : org.zzl.fp.play.FunctionalRandomGenerators.Generator[Int]{val ra
                                                  //| nd: java.util.Random} = org.zzl.fp.play.FunctionalRandomGenerators$$anonfun$
                                                  //| main$1$$anon$1@36d64342
                                                  
	val booleans = new Generator[Boolean] {
		def generate = integers.generate > 0
	}                                         //> booleans  : org.zzl.fp.play.FunctionalRandomGenerators.Generator[Boolean] = 
                                                  //| org.zzl.fp.play.FunctionalRandomGenerators$$anonfun$main$1$$anon$2@39ba5a14
                                                  //| 
                                                  
	val pairs = new Generator[(Int, Int)] {
		def generate = (integers.generate, integers.generate)
	}                                         //> pairs  : org.zzl.fp.play.FunctionalRandomGenerators.Generator[(Int, Int)] = 
                                                  //| org.zzl.fp.play.FunctionalRandomGenerators$$anonfun$main$1$$anon$3@3d24753a
                                                  //| 
	
	
}