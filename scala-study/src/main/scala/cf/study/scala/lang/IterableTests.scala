package cf.study.scala.lang

import org.junit.Test

import scala.collection.mutable.ListBuffer

/**
  * Created by fan on 2016/9/10.
  */
class IterableTests {

	@Test
	def testNull: Unit = {
		val iterable: Iterable[Int] = null
		println(iterable.size)
	}

	@Test
	def testListBuf: Unit = {
		println(ListBuffer.empty[Object].isInstanceOf[Iterable[Object]])
	}
}
