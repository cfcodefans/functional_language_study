package cf.study.hankerrank

import java.util._

/**
  * Created by fan on 2016/11/12.
  * https://www.hackerrank.com/challenges/largest-rectangle?h_r=next-challenge&h_v=zen
  */
class LargestRect {
	def solution(hs: Array[Int]): Int = {
		if (hs.isEmpty) return 0
		if (hs.length == 1) return hs(0)

		var maxArea: Int = 0
		val treeMap: TreeMap[Int, TreeSet[Int]] = new TreeMap[Int, TreeSet[Int]]

		for (i <- 0 until hs.length) {
			val h: Int = hs(i)
			var treeSet: TreeSet[Int] = treeMap.get(h)
			if (treeSet == null) {
				treeSet = new TreeSet[Int]
				treeMap.put(h, treeSet)
			}
			treeSet.add(i)
		}

		for (i <- 0 until hs.length - 1) {
			val h: Int = hs(i)
			val headMap: SortedMap[Int, TreeSet[Int]] = treeMap.headMap(h)
			headMap.entrySet()
		}

		return maxArea
	}
}
