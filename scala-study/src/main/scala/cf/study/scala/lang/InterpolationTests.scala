package cf.study.scala.lang

import org.junit.Test

/**
  * Created by fan on 2016/7/25.
  */
case class MockParser(override val parts: String*) extends StringContext {
	def p(args: Any*):String = {
		val sb: StringBuilder = new StringBuilder

		val minSize: Int = Math.min(parts.size, args.size)
		1.to(minSize).foreach(i => sb.append(parts(i)).append(args(i)))
		val rest:Seq[Any] = if (args.size > parts.size) args else parts
		rest.foreach(sb.append(_))

		return sb.toString
	}
}

class InterpolationTests {
	import MockParser._
	@Test def testParser: Unit = {
		val str = "abc"
		//can't work, so StringContext is a syntactic sugar
//		println(p"123${str}456")
	}
}
