package cf.study.scala.lang

import org.junit.{Assert, Test}

/**
 * Created by fan on 2015/11/11.
 */
class HierarchyTests {
	@Test def testAny(): Unit = {
		{
			val a = "a"
			val a1 = a
			Assert.assertTrue(a == a1)
			Assert.assertTrue(a equals a1)
			Assert.assertTrue(a eq a1)
		}

		{
			val a = "a"
			val a1 = "a"
			Assert.assertTrue(a == a1)
			Assert.assertTrue(a equals a1)
			Assert.assertTrue(a eq a1)
		}

		{
			val a = "a"
			val a1 = new String(a)
			Assert.assertTrue(a == a1)
			Assert.assertTrue(a equals a1)
			//class AnyRef defines
			//an additional eq method, which cannot be overridden and is implemented
			//as reference equality (i.e., it behaves like == in Java for reference types)
			Assert.assertFalse(a eq a1)
		}

		{
			val a = null
			Assert.assertTrue(a == null)
			Assert.assertTrue(null == a)
			Assert.assertTrue(a eq null)
			Assert.assertTrue(null eq a)
		}
	}
}
