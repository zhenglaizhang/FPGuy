package org.zzl.fp.util

object StrUtil {
  def upper(strings: String*): Seq[String] = strings.map { s => s.toUpperCase }
}

// StrUtil.upper("hello")
// StrUtil.upper()
// StrUtil.upper("hello", "world")