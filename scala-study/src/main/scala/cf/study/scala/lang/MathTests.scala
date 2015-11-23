package cf.study.scala.lang

import java.math.MathContext

import scala.math.BigDecimal

import org.junit.{Assert, Test}

/**
 * Created by fan on 2015/11/2.
 */
object MathTests {
	def pi(_n: Int): BigDecimal = {
		val n = if (_n == null || _n < 1) 0 else _n
		val mc: MathContext = new MathContext(n)
		var _pi: BigDecimal = BigDecimal(0, mc)
		val one: BigDecimal = BigDecimal.valueOf(1, mc)
		val two: BigDecimal = BigDecimal.valueOf(2, mc)
		val four: BigDecimal = BigDecimal.valueOf(4, mc)

		for (k <- 0 to n) {
			val tmp1:BigDecimal = one / (BigDecimal(16, mc).pow(k))
			val tmp2_1:BigDecimal = four / (BigDecimal(8 * k + 1, mc))
			val tmp2_2:BigDecimal = two / (BigDecimal(8 * k + 4, mc))
			val tmp2_3:BigDecimal = one / (BigDecimal(8 * k + 5, mc))
			val tmp2_4:BigDecimal = one / (BigDecimal(8 * k + 6, mc))
			val tmp2:BigDecimal = tmp2_1 - (tmp2_2) - (tmp2_3) - (tmp2_4)

			_pi = _pi + tmp1 * tmp2
		}

		_pi
	}

	def pi2Longs(n: Int): List[Long] = {
		MathTests.pi(n).toString().substring(0, n + 1).filter(c => c.isDigit).map(c => c.toLong - 48).toList
	}
}

class MathTests {
	@Test def testPI(): Unit = {
		println(MathTests.pi2Longs(5))
		println(MathTests.pi(10))
	}

	@Test def testOperOrder(): Unit = {
		Assert.assertEquals(1 + 2 * 3 + 1, 8)

		val one = BigDecimal.valueOf(1)
		val two = BigDecimal.valueOf(2)
		val three = BigDecimal.valueOf(3)
		val eight = BigDecimal.valueOf(8)

		Assert.assertEquals(one + two * three + one, eight)
	}
}