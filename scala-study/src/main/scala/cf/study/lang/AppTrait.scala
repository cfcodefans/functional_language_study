package cf.study.lang

import ChecksumAccumulator.calculate
/**
 * Created by fan on 2015/6/25.
 */

object  FallWinterSpringSummer extends App {
	for (season <- List("fall", "winter", "spring"))
		println(season + ": " + calculate(season))
}
