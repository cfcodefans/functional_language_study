package cf.study.hankerrank

import org.junit.Test

import scala.util.control.Breaks._

/**
  * Created by fan on 2016/11/3.
  */
object GreaterIsGood {

	def swap(arr:Array[Char], a: Int, b: Int):Array[Char] = {
		val c:Char = arr(a)
		arr(a) = arr(b)
		arr(b) = c;
		return arr
	}

	def isDescending(str: String): Boolean = {
		for (i <- 0.to(str.length - 2)) {
			if (str(i) < str(i + 1)) return false
		}
		return true
	}

	def greater(str: String): String = {
		if (str.length == 1 || isDescending(str)) return "no answer"

		val arr: Array[Char] = str.toCharArray

		var reverse:Int = -1
		breakable {
			for (i <- (arr.length - 1).to(1, -1)) {
				if (arr(i) > arr(i - 1)) {
					reverse = i
					break
				}
			}
		}

		if (reverse == arr.length - 1) {
			swap(arr, reverse, reverse - 1)
			return new String(arr)
		}

		val v1:Char = arr(reverse - 1)

		val (head, rear) = arr.splitAt(reverse)

		val selected: Int = rear.zipWithIndex.filter(_._1 > v1).minBy(_._1)._2
		head(reverse - 1) = rear(selected)
		rear(selected) = v1

		return new String(head) + new String(rear.sorted)
	}
}

class GreaterIsGood {
	import GreaterIsGood._

	@Test
	def test: Unit = {
		println(greater("ehdegnmorgafrjxvksc"))
	}
}
