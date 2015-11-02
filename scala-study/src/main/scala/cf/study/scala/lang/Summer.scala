package cf.study.scala.lang

import ChecksumAccumulator.calculate
import org.junit.Test
import java.nio.file.Paths
import java.lang

/**
 * Created by fan on 2015/6/25.
 */
object Summer {
	def main (args: Array[String]) {
		for (arg <- args)
			println(arg + ": " + calculate(arg))
	}
}

class Summer {
	@Test def testCompileAndRun(): Unit = {
//		println(Paths.get(".").toAbsolutePath)
//		val bp: ProcessBuilder = new ProcessBuilder("cmd", "/c", "echo %SCALA_HOME%")
//		val proc = bp.start();
//		proc.getInputStream.
	}
}
