package cf.study.scala.lang

import org.apache.commons.lang3.builder.ToStringBuilder
import org.junit.{Assert, Test}

/**
 * Created by fan on 2015/12/29.
 */
class StatefulTests {

	class BankAccount {
		private var bal: Int = 0;
		def balance: Int = bal
		def deposit(amount: Int): Unit = {
			require(amount > 0, "Amount must be greater than zero")
			bal += amount
		}
		def withdraw(amount: Int): Boolean = {
			if (amount > bal)
				return false
			bal -= amount
			true
		}

		override def toString(): String = ToStringBuilder.reflectionToString(this)
	}
	@Test def study(): Unit = {
		val account = new BankAccount
		account.deposit(100)
		println(account)
		Assert.assertTrue(account.withdraw(80))
		println(account)
		Assert.assertFalse(account.withdraw(80))
	}
}
