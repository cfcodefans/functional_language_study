package cf.study

import java.math._

/**
 * Created by fan on 2016/11/14.
 */
object TestUtils {

  def pi(_n: Integer): BigDecimal = {
    var n = _n
    if (n == null) {
      n = 0
    }

    if (n == 0)
      return null
    if (n == 1)
      return new BigDecimal(3)
    if (n == 2)
      return new BigDecimal(3.1)
    if (n == 3)
      return new BigDecimal(3.14)

    val mc: MathContext = new MathContext(n)
    var pi: BigDecimal = new BigDecimal(BigInteger.ZERO, n, mc)
    val one: BigDecimal = new BigDecimal(BigInteger.ONE, n, mc)
    val two: BigDecimal = new BigDecimal(new BigInteger("2"), n, mc)
    val four: BigDecimal = new BigDecimal(new BigInteger("4"), n, mc)
    for (k <- 0 until n) {
      val tmp1: BigDecimal = one.divide(new BigDecimal(16).pow(k, mc), mc)

      val tmp2_1: BigDecimal = four.divide(new BigDecimal(8 * k + 1, mc), mc)
      val tmp2_2: BigDecimal = two.divide(new BigDecimal(8 * k + 4, mc), mc)
      val tmp2_3: BigDecimal = one.divide(new BigDecimal(8 * k + 5, mc), mc)
      val tmp2_4: BigDecimal = one.divide(new BigDecimal(8 * k + 6, mc), mc)

      val tmp2: BigDecimal = tmp2_1.subtract(tmp2_2, mc).subtract(tmp2_3, mc).subtract(tmp2_4, mc)
      pi = pi.add(tmp1.multiply(tmp2, mc))
    }

    // pi.setScale(n, BigDecimal.ROUND_UP)
    pi.scaleByPowerOfTen(n)

    return pi
  }

  def pi2Longs(n: Integer): List[Long] = {
    val _pi: BigDecimal = pi(n)

    return _pi.toString.filter(_.isDigit).map(_.toLong - 48).toList
  }

}
