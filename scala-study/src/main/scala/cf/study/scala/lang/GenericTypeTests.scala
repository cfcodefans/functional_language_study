package cf.study.scala.lang

import org.junit.Test

/**
  * Created by fan on 2016/10/24.
  */
//trait TConfig[VT <: java.io.Serializable] extends (String, String) with java.io.Serializable {
//    def key: String = _1
//
//    def value: VT
//}

class GenericTypeTests {
    @Test
    def testTypeUpperBoundary: Unit = {
        val n: Int = 0
        //        println(ClassLens.prespective(classOf[TConfig[_]]))
        //        class IntCfg(k: String, v: String) extends (String, String)(k, v) with TConfig[Integer] {
        //            override def value: Integer = _2.toInt
        //        }
        //        println(ClassLens.prespective(classOf[IntCfg]))
    }

    @Test
    def testTypeAndImplicit: Unit = {
        //        class BoolCfg(k: String, v: String) extends (String, String)(k, v) with TConfig[java.lang.Boolean] {
        //            override def value: java.lang.Boolean = java.lang.Boolean.valueOf(_2)
        //        }
    }

    @Test
    def testTypePlaceholder = {
        val arr: Array[String] = Array("a", "b", "c")
        var _arr: Array[_] = arr

        println(arr)
        println(_arr)

        val i_arr: Array[Int] = Array(0, 1, 2)
        _arr = i_arr
        println(i_arr)
        println(_arr)
    }

    @Test
    def testManifest = {
        def foo[T](x: Array[T])(implicit m: Manifest[T]) = {
            println(s"Array[${m}](${x.mkString(",")})")
        }

        val arr: Array[String] = Array("a", "b", "c")
        var _arr: Array[_] = arr

        foo(arr)
        foo(_arr)
    }
}
