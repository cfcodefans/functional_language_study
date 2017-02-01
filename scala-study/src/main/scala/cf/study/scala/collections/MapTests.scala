package cf.study.scala.collections

import org.apache.commons.lang3.StringUtils
import org.junit.Test

import scala.collection.concurrent.TrieMap
import scala.collection.immutable.TreeMap
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
  * Created by fan on 2016/1/25.
  */
class MapTests {

    @Test def testMap: Unit = {
        val m: Map[Int, Int] = Map(1 -> -1)
        println(m)
        println(m(1))

        println(m.get(0))
        println(m.get(0).getOrElse(null))

        println(m(0))
        //        println(m(1))
    }


    @Test def testMutableMap: Unit = {
        val m = TrieMap.empty[Int, String]

        def generator(i: Int): String = {
            println(s"generator(${i})")
            StringUtils.repeat(i.toString, i)
        }

        println(m.getOrElseUpdate(5, generator(5)))
        println(m.getOrElseUpdate(5, generator(5)))
        println(m.getOrElseUpdate(6, {
            println("generator_2")
            generator(6)
        }))
    }


    @Test def testToMap: Unit = {
        val toMap: Map[Int, String] = 1.to(10).map(i => (i, i.toString)).toMap
        println(toMap)

        val array: Array[Int] = Array(3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5, 8, 9, 7, 9, 3, 2, 6)
        val _m: TrieMap[Int, mutable.Buffer[String]] = array.map(i => (i, i.toString))
            .foldLeft(TrieMap.empty[Int, mutable.Buffer[String]])((m, el) => {
                val buf: mutable.Buffer[String] = m.getOrElseUpdate(el._1, ListBuffer.empty[String])
                println(s"buf: ${buf}")
                buf += el._2
                println(s"buf: ${buf}")
                m
            })
        println(_m)

        _m.size
    }

    @Test
    def testTreeMap: Unit = {
        val tm: TreeMap[Int, Int] = TreeMap.empty[Int, Int]
    }

    @Test def testMapGet(): Unit = {
        val map: mutable.Map[Int, String] = mutable.Map.empty[Int, String]
        map.get(1).map(println(_))
        map.put(1, 1.toString)
        map.get(1).map(println(_))
    }
}
