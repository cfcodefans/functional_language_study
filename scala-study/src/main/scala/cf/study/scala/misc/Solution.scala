object Solution {


	private val ROW: Int = 32
	private val COL: Int = 63
	val b: Array[Array[Char]] = Array.ofDim(ROW, COL)

	def reset() {
		b.foreach(line => 0.to(line.length - 1).foreach(col => line(col) = '_'))
	}

	def printBoard {
		0.to(b.length - 1)foreach(line => println(b(line).mkString))
	}

	def drawLine(line: Array[Char], a: Int, b: Int, c: Char): Array[Char] = {
		a.to(b).foreach(col => line(col) = c)
		line
	}

	def drawTriangle(rs: Int, re: Int, cs: Int, ce: Int, c: Char): Unit = {
		val h: Int = re - rs
		val m: Int = (cs + ce) / 2
		0.to(h).foreach(row => {
			drawLine(b(rs + row), m - row, m + row, c)
		})
	}

	def _drawTriangle(rs: Int, re: Int, cs: Int, ce: Int, c: Char): Unit = {
		val h: Int = re - rs
		val m: Int = (cs + ce) / 2
		0.to(h).foreach(row => {
			val i: Int = h - row
			drawLine(b(rs + row), m - i, m + i, c)
		})
	}

	def iterate(rs: Int, re: Int, cs: Int, ce: Int): Array[(Int, Int, Int, Int)] = {
		if (Math.abs(rs - re) < 3) return Array.empty

		val rm: Int = (rs + re) / 2
		val cr: Int = (ce - cs) / 2
		val cm: Int = (ce + cs) / 2
		return Array((rs, rm, cm - cr/2, cm + cr/2),
			(rm, re, cs, cm),
			(rm, re, cm + 1, ce))
	}

	def drawTriangles(n: Int, rs: Int, re: Int, cs: Int, ce: Int) {
		if (n == 0) return
		_drawTriangle((rs + re) / 2 + 1, re, cs, ce, '_')
		val boxes: Array[(Int, Int, Int, Int)] = iterate(rs, re, cs, ce)
//		println(boxes.mkString("\n"))
//		println()
		boxes.foreach(box => drawTriangles(n - 1, box._1, box._2, box._3, box._4))
	}

	def main(args: Array[String]) {
		reset()
		drawTriangle(0, 31, 0, 62, '1')
		//		_drawTriangle(16, 31, 0, 63, '_')
		drawTriangles(5, 0, 31, 0, 62)
		printBoard
	}
}
