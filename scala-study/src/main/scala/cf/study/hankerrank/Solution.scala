package cf.study.hankerrank

import java.util
import java.util._

import scala.collection.mutable
import scala.util.control.Breaks._

object Solution {
    var sum: Long = 0
    var count: Int = 0


    def distSumToRoot(lv: Long): Long = lv * (lv + 1) / 2

    class TN(val v: Int, val lv: Int, var left: TN, var right: TN, var parent: TN) {
        def this(_v: Int, _lv: Int) = this(_v, _lv, null, null, null)

        def this(_v: Int, parent: TN) = this(_v, parent.lv + 1, null, null, parent)

        var numOfLeft: Int = 0
        var distSumOfLeft: Long = 0
        var numOfRight: Int = 0
        var distSumOfRight: Long = 0
        var depthOfLeft: Int = 0
        var depthOfRight: Int = 0

        private def updateParents() {
            var node: TN = this
            do {
                if (node.v > node.parent.v) {
                    node.parent.numOfRight += 1
                } else {
                    node.parent.numOfLeft += 1
                }

                if (this.v > node.parent.v) {
                    node.parent.distSumOfRight += this.lv - node.parent.lv
                } else {
                    node.parent.distSumOfLeft += this.lv - node.parent.lv
                }
                node = node.parent
            } while (node.parent != null)


            if (node.v > node.parent.v) {
                node.parent.depthOfRight += 1
            } else {
                node.parent.depthOfLeft += 1
            }
            if (node.parent.right != null && node.parent.left != null) {
                return
            }

            node = node.parent
            do {
                if (node.v > node.parent.v) {
                    node.parent.depthOfRight += 1
                } else {
                    node.parent.depthOfLeft += 1
                }
                node = node.parent
            } while (node.parent != null)
        }

        def updateSum: Unit = {
            var node: TN = this

            do {
                if (v > node.parent.v) {
                    sum += node.parent.distSumOfLeft + node.parent.numOfLeft * (this.lv - node.parent.lv)
                } else if (v < node.parent.v) {
                    sum += node.parent.distSumOfRight + node.parent.numOfRight * (this.lv - node.parent.lv)
                }
                node = node.parent
            } while (node.parent != null)


            sum += distSumToRoot(this.lv)
        }

        {
            if (root != null) {
                updateSum
                updateParents()
            }
        }

        def addNode(_v: Int) {
            var node = this

            do {
                if (_v > node.v) {
                    if (node.right != null) {
                        node = node.right
                    } else {
                        node.right = new TN(_v, node)
                        return
                    }
                } else {
                    if (node.left != null) {
                        node = node.left
                    } else {
                        node.left = new TN(_v, node)
                        return
                    }
                }
            } while (node != null)
        }
    }

    var root: TN = _


    def main(args: Array[String]) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution
*/
        val scan = new java.util.Scanner(System.in)
        //		val scan: Scanner = new Scanner(new FileInputStream(new File("input12.txt")))
        val n: Int = scan.nextInt()

        root = new TN(scan.nextInt(), 0)
        println(sum)

        for (i: Int <- 1.to(n)) {
            val newValue: Int = scan.nextInt()
            root.addNode(newValue)
            println(newValue, sum)
        }
    }

    val m: Map[Char, Char] = new HashMap[Char, Char]

    def solve(str: String): String = {
        if (str.length() % 2 == 1) return "NO"
        val s: Stack[Char] = new Stack[Char]

        // println(str)
        breakable {
            str.foreach(c => {
                // println(c)
                if (m.containsKey(c)) {
                    val _c = m.get(c)
                    // println(s"_c: ${_c} s.peek: ${s.peek}")
                    if (s.empty || _c != s.pop) {
                        return "NO"
                        break
                    }
                } else {
                    s.push(c)
                }
            })
        }

        if (s.empty)
            return "YES"
        else
            return "NO"
    }

    def displayPathtoPrincess(m: Int, grid: Array[String]) = {
        val robotPos: (Int, Int) = grid.zipWithIndex.filter(_._1.contains('m')).map(p => (p._1.indexOf('m'), p._2)).head
        val princessPos: (Int, Int) = {
            val height: Int = grid.size - 1
            if (grid(0).charAt(0) == 'p') (0, 0)
            else if (grid(height).charAt(0) == 'p') (0, height)
            else if (grid(0).last == 'p') (grid(0).length - 1, 0)
            else (grid.last.size, height)
        }

        println({
            if (robotPos._1 < princessPos._1) "RIGHT" else "LEFT"
        })
        println({
            if (robotPos._2 < princessPos._2) "DOWN" else "UP"
        })
    }

    def minDistanceBetweenSameElementsInArray(arr: Array[Int]): Int = {
        val map: mutable.Map[Int, Int] = mutable.Map.empty
        var re: Int = -1
        for (i <- 0 until (arr.length)) {
            val v: Int = arr(i)
            if (map.contains(v)) {
                re = if (re == -1) i - map(v) else Math.min(re, i - map(v))
            }
            map.update(v, i)
        }
        return re
    }
}