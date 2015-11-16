package cf.study.scala.lang


import org.junit.Test

/**
 * Created by fan on 2015/11/16.
 */

package bobsrockets.naviagetion {
	class Navigator
}

package launch {
	class Booster3
}

package bobs {
	package rockets {
		package navigation {
			class StarMap
			class Navigator {
				//No need to import StarMap
				val map = new StarMap
			}
			package tests {
				class NavigatorTests {
					@Test def navTests(): Unit = {
						// doesn't need to import as inside the package
						val nav = new Navigator
					}
				}
			}

			package launch {
				class Booster1
			}

			class MissionControl {
				val booster1 = new launch.Booster1
				//different 'launch' package
				val booster2 = new bobs.rockets.launch.Booster2
//				val booster3 = new _root_.launch.Booster3
				//doesn't work as exampled
				val nav = new bobsrockets.naviagetion.Navigator
			}
		}

		package launch {
			class Booster2
		}

		class Ship {
			//need to specify the Navigator as it is in child scope
			val nav = new navigation.Navigator
		}

		package fleets {
			class Fleet {
				//No need to import Ship as ship is in upper scope
				def addShip() {new Ship}
			}
		}
	}
}

class Outer {
	class Inner {
		private def f() {
			println("f")
		}
		class InnerMost {
			f() //OK
		}
	}
//	(new Inner).f()//error: f is not accessible
}

package p {
	class Super {
		protected def f() {println("f")}
		class Inner {
			f()
		}
	}
	class Sub extends Super {
		f()
	}
	class Other {
//		(new Super).f()//error: f is not accessible
	}
}

package scope {
	package a {
		private[a] class A {
			private[a] def f() {println("scope.a.A.f()")}
		}
		private[scope] class _A {
			private[a] def f() {println("scope.a.A.f()")}
		}
		class Tests {
			(new A).f()
		}
		package a._1 {
			class Tests {
				(new A).f()
			}
		}
	}
	package b {
		class Tests {
			import scope.a._
//			(new A).f() //A is invisible
//			(new _A).f()//_A.f is invisible
		}
	}
}

class PackageAndImportTests {
	@Test def packageTest(): Unit = {
		// need to import outside of the package
		import cf.study.scala.lang.bobsrockets.naviagetion.Navigator
		val nav = new Navigator

		{
			import bobs.rockets._
			val ship = new Ship
			val nav = new navigation.Navigator
			{
				import bobs.rockets.navigation._
				val starMap = new StarMap
			}

			import bobs.rockets.navigation.{MissionControl => MC}
			val mc = new MC
		}

	}

	@Test def testPackageObject(): Unit = {
		PackageObject.foo()
	}

}

package object PackageObject {
	def foo(): Unit = {
		println(this)
		println(this.getClass)
	}
}