package cf.study.hankerrank

import scala.collection.mutable.ListBuffer

/**
  * Created by fan on 2016/9/22.
  */
object DynamicArray {
	def main(args: Array[String]) {
		/* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution
*/
		var lastAns: Int = 0
		val scan = new java.util.Scanner(System.in)
		val N: Int = scan.nextInt()
		val Q: Int = scan.nextInt()

		val seqList: Array[ListBuffer[Int]] = Array.ofDim(N)
		1.to(N).foreach(i => seqList(i) = ListBuffer.empty)

		for (i <- 0.to(Q)) {
			val qt: Int = scan.nextInt()
			val x: Int = scan.nextInt()
			val seq: ListBuffer[Int] = seqList((x ^ lastAns) % N)
			val y: Int = scan.nextInt()
			qt match {
				case 1 => {
					seq += y
				}
				case 2 => {
					lastAns = seq(y % seq.length)
					println(lastAns)
				}
				case _ =>
			}
		}
	}
}
