package cf.study.scala.collections

import org.junit.Test

/**
  * Created by Administrator on 2016/9/26.
  */
class ArrayTests {
	@Test def testSubArray: Unit = {
		val arr:Array[Int] = 0.to(10).toArray

		println(arr.slice(0,5).mkString(" "))
		println(arr.slice(5,10).mkString(" "))
	}
}
