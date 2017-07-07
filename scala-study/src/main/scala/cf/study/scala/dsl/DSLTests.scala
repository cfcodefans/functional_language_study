package cf.study.scala.dsl

import org.junit.Test

case class Cpu(core: Int, arch: String)

class CpuInt(core: Int) {
    def cores(arch: String): Cpu = Cpu(core, arch)
}

class Machine {
    var cpu: Cpu = null
    var os: String = null

    def having(cpu: Cpu): Machine = {
        this.cpu = cpu
        this
    }

    def os(os: String): Machine = {
        this.os = os
        this
    }

    override def toString: String = s"Machine($cpu, $os)"
}

object DSLTests {
    implicit def int2CpuInt(i: Int): CpuInt = new CpuInt(i)
}

/**
  * Created by fan on 2017/7/7.
  * from http://git.bookislife.com/post/2015/jgsk-scala-05-dsl/
  */
class DSLTests {

    import DSLTests._

    @Test
    def testDSL_1(): Unit = {
        val machine: Machine = new Machine
        val m1 = machine having (8 cores "64bit") os "Linux"
        println(m1)
        val m2 = machine having (4 cores "32bit") os "Windows"
        println(m2)
    }

}
