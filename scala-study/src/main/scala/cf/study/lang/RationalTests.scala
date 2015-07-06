package cf.study.lang

import org.junit.{Assert, Test}

/**
 * Created by fan on 2015/7/6.
 */



class RationalTests {
	@Test def testV1(): Unit = {
		class Rational_v1(n: Int, d: Int)
		val r1 = new Rational_v1(1, 2)
		println(r1)
	}

	@Test def testV2(): Unit = {
		class Rational_v2(n: Int, d: Int) {
			println("Primary constructor: " + n + "/" + d)
		}
		val r2 = new Rational_v2(1, 2)
		println(r2)
	}

	@Test def testV3(): Unit = {
		class Rational_v3(n: Int, d: Int) {
			override def toString = n + "/" + d
		}
		val r3 = new Rational_v3(1, 2)
		println(r3)
		println(new Rational_v3(1, 3))
		println(new Rational_v3(5, 7))
	}

	@Test def testV3_1(): Unit = {
		class Rational_v31(n: Int, d: Int) {
			//re is taken as property
			val re = n.toFloat / d.toFloat
			override def toString = n + "/" + d
			println("float: " + re)

			if (n > d) {
				val t = n - d
				println("t = " + t)
			}
		}
		val r3 = new Rational_v31(1, 2)
		println(r3)
		println(new Rational_v31(1, 3))
		println(new Rational_v31(5, 7))
		val r4 = new Rational_v31(5, 3)
		println(r4, r4.re, r4)
	}

	@Test def testV4(): Unit = {
		class Rational(n: Int, d: Int) {
			require(d != 0)
			override def toString = n +"/" + d
		}
		val r = new Rational(1, 1)
		println(r)
		val r1 = new Rational(1, 0)
		println(r1)
	}

	@Test def testV5(): Unit = {
		// doesn't have n and d as properties of Rational
//		class Rational(n: Int, d: Int) {//this won't compile
//			require(d != 0)
//			override def toString = n + "/" + d
//			def add(that: Rational): Rational =
//				new Rational(n * that.d + that.n * d, d * that.d)
//		}

		class Rational(n: Int, d: Int) {
			require(d != 0)
			val numer: Int = n
			val denom: Int = d
			override def toString= numer + "/" + denom
			def add(that: Rational): Rational =
				new Rational(numer * that.denom + that.numer * denom,
							 denom * that.denom)
		}

		val r = new Rational(2, 3)
		val r1 = r.add(new Rational(4, 3))
		println(r1, r1.numer, r1.denom)
	}

	@Test def testSelfReference(): Unit = {
		class Rational(n: Int, d: Int) {
			require(d != 0)
			val numer: Int = n
			val denom: Int = d
			override def toString= numer + "/" + denom
			def add(that: Rational): Rational =
				new Rational(numer * that.denom + that.numer * denom,
					denom * that.denom)
			def lessThan(that: Rational)=
				this.numer * that.denom < that.numer * this.denom

			def max(that: Rational) = //this.lessThan(that) ? that : this
				if (this.lessThan(that)) that else this
		}

		Assert.assertTrue(new Rational(2, 5).lessThan(new Rational(5, 11)))
	}

	@Test def testAuxiliaryConstructors(): Unit = {
		class Rational(n: Int, d: Int) {
			require(d != 0)
			val numer: Int = n
			val denom: Int = d
			def this(n: Int) = this(n, 1) //auxiliary constructor
			override  def toString = numer + "/" + denom
			def add(that: Rational): Rational =
				new Rational(numer*that.numer + that.numer * denom, denom * that.denom)
		}

		println(new Rational(5))
	}

	@Test def testPrivateFieldsAndMethods(): Unit = {
		class Rational(n: Int, d: Int) {
			require(d != 0)
			private def gcd(a: Int, b: Int): Int =
				if (b == 0) a else gcd(b, a % b)

			private val g = gcd(n.abs, d.abs)

			val numer = n / g
			val denom = d / g

			override def toString = numer + "/" + denom
			def add(that: Rational): Rational =
				new Rational(numer * that.denom + that.numer * denom,
							denom * that.denom)
		}

		println(new Rational(64, 36))
	}

	@Test def testOperators(): Unit = {
		class Rational(n: Int, d: Int) {
			require(d != 0)
			private def gcd(a: Int, b: Int): Int=
				if (b == 0) a else gcd(b, a % b)

			private val g = gcd(n, d)
			val numer = n / g
			val denom = d / g
			def this(n: Int) = this(n, 1)

			override def toString = numer + "/" + denom

			def +(that: Rational): Rational =
				new Rational(numer * that.denom + that.numer * denom, denom * that.denom)

			def +(that:Int): Rational=
				this + new Rational(that)

			def -(that:Rational): Rational=
				this + that * -1

			def -(that: Int): Rational =
				this - new Rational(that)

			def *(that: Int): Rational =
				this * new Rational(that)

			def *(that: Rational): Rational =
				new Rational(numer * that.numer, denom * that.denom)

			def /(that: Rational): Rational =
				this * new Rational(that.denom, that.numer)

			def /(that: Int): Rational =
				this / new Rational(that)

		}
		implicit def intToRational(x: Int) = new Rational(x)

		val x = new Rational(1, 2)
		val y = new Rational(2, 3)
		println(x, "+",  y, x + y)
		println(x, "*",  y, x * y)
		println(x, "+",  1, x + 1)
		println(x, "+",  2, x * 2)
	}

}
