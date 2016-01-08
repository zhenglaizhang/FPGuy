package org.zzl.fp.util

object FileUtil {
 
  def countLines(fileNames: String*) = {
    import scala.io.Source
    import scala.util.control.NonFatal
    def count(fileName: String) = {
      println()
      var source: Option[Source] = None
      try {
        source = Some(Source.fromFile(fileName))
        val size = source.get.getLines.size
        println(s"file $fileName has $size lines")
      } catch {
        case NonFatal(ex) => println(s"Non fatal exception! $ex")
      } finally {
        for (s <- source) {
          println(s"Closing $fileName")
          s.close 
        }
      }
    }
    
    fileNames foreach count
  }
  
}