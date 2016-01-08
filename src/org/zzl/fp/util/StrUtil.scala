package org.zzl.fp.util

object StrUtil {
  def upper(strings: String*): Seq[String] = strings.map(s => s.toUpperCase)
  
  def lower(strings: String*) = strings.map(_.toLowerCase)
  
  
  def joiner(strings: String*): String = strings.mkString("-")
}

// StrUtil.upper("hello")
// StrUtil.upper()
// StrUtil.upper("hello", "world")