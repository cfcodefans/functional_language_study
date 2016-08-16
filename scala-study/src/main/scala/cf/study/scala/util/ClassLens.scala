package cf.study.scala.util

import java.lang.annotation.Annotation
import java.lang.reflect.{Executable, Field, Method, Parameter, Modifier => Mod}

import org.apache.commons.lang3.StringUtils
import org.junit.Test

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
  * Created by Administrator on 2016/2/27.
  */
object ClassLens {
	def typeStr[T](cls: Class[T]): String = {
		if (cls.isAnnotation) return "@interface"
		if (cls.isInterface) return "interface"
		if (cls.isEnum) return "enum"

		return "class"
	}

	def modsToStrs(mod: Int): List[String] = {
		val mods: ListBuffer[String] = new ListBuffer[String]

		if (Mod.isPublic(mod)) mods += "public"
		if (Mod.isProtected(mod)) mods += "protect"
		if (Mod.isPrivate(mod)) mods += "private"

		if (Mod.isStatic(mod)) mods += "static"

		if (Mod.isFinal(mod)) mods += "final"
		if (Mod.isAbstract(mod)) mods += "abstract"
		if (Mod.isNative(mod)) mods += "native"

		if (Mod.isSynchronized(mod)) mods += "synchronized"

		return mods.toList
	}

	def prespective[T](cls: Class[T]): String = {
		val imports: ListBuffer[Class[_]] = new ListBuffer[Class[_]]
		imports += cls.getSuperclass
		val lines: ListBuffer[String] = new ListBuffer[String]

		def processAnnotations(ans: Array[Annotation], prefix: String = ""): Unit = {
			if (ans.isEmpty) return;

			imports.appendAll(ans.map(_.annotationType()))
			lines.appendAll(ans.map(prefix + _.annotationType().getName))
		}

		lines += "package %s;".format(cls.getPackage.getName)

		processAnnotations(cls.getAnnotations, "@")

		lines += "%s %s %s extends %s".format(modsToStrs(cls.getModifiers).mkString(" "),
			typeStr(cls),
			cls.getSimpleName, {
				if (cls.getSuperclass != null) cls.getSuperclass.getSimpleName else ""
			})

		val infs: Array[Class[_]] = cls.getInterfaces
		imports.appendAll(infs)
		if (!infs.isEmpty) {
			lines += "\timplements %s {\n".format(infs.map(_.getSimpleName).mkString(" ", ", ", " "))
		} else {
			lines.update(lines.length - 1, lines.last + " {")
		}

		def fieldToLine(f: Field) = "\t%s %s %s;".format(modsToStrs(f.getModifiers).mkString(" "), f.getType.getSimpleName, f.getName)

		def paramToStr(p: Parameter): String = {
			val anns: Array[Annotation] = p.getAnnotations
			imports.appendAll(anns.map(_.annotationType()))
			return "%s %s %s".format(anns.map(_.toString).mkString(" "), p.getType.getSimpleName, p.getName)
		}

		def throwToStr(m: Executable): String = {
			val exs: mutable.ArrayOps[Class[_]] = m.getExceptionTypes
			if (exs.isEmpty) return ""
			imports.appendAll(exs)
			return " throws %s;".format(exs.map(_.getSimpleName).mkString("", ",\n\t", ""))
		}

		def methodToLine(m: Method) = {
			imports += m.getReturnType
			val params: Array[Parameter] = m.getParameters
			imports.appendAll(params.map(_.getType))
			"\t%s %s %s(%s)%s;".format(modsToStrs(m.getModifiers).mkString(" "),
				m.getReturnType.getSimpleName,
				m.getName,
				params.map(paramToStr(_)).mkString(",\n\t"), throwToStr(m))
		}

		val mds: Array[Method] = cls.getDeclaredMethods
		val fs: Array[Field] = cls.getDeclaredFields
		imports.appendAll(fs.map(_.getType))

		def procLine(f: Field) {
			processAnnotations(f.getAnnotations, "\t@")
			imports.append(f.getType)
			lines += fieldToLine(f)
		}

		lines += "//static fields"
		fs.filter(f => Mod.isStatic(f.getModifiers)).sortBy(_.getModifiers).foreach(procLine(_))
		lines += "//static methods"
		lines.appendAll(mds.filter(m => Mod.isStatic(m.getModifiers)).sortBy(_.getModifiers).map(methodToLine(_)))

		cls.getConstructors.foreach(c => {
			lines += "\n"
			processAnnotations(c.getAnnotations, "\t")

			val params: Array[Parameter] = c.getParameters
			imports.appendAll(params.map(_.getType))
			lines += "\t%s %s(%s)%s;\n".format(modsToStrs(c.getModifiers).mkString(" "),
				c.getName,
				params.map(paramToStr(_)).mkString(",\n\t"), throwToStr(c))
		})

		lines += "//fields"
		fs.filter(f => !Mod.isStatic(f.getModifiers)).sortBy(_.getModifiers).foreach(procLine(_))
		lines += "//methods"
		lines.appendAll(mds.filter(m => !Mod.isStatic(m.getModifiers)).sortBy(_.getModifiers).map(methodToLine(_)))

		lines += "}"

		if (!imports.isEmpty) {
			lines.insert(1, "\n")
			val top = classOf[Object].getPackage
			lines.insertAll(1, imports.filter(_ != null).filter(c => top.equals(c.getPackage))
				.map(c => if (c.isArray) c.getComponentType.getName else c.getName)
				//					.map(n => {println(n); StringUtils.substringBefore(n, "\n");})
				.distinct.map("import %s;".format(_)).sorted)
		}

		return lines.filter(line => StringUtils.isNotBlank(line)).mkString("\n")
	}
}

class ClassLensTests {

	import ClassLens._

	@Test def test: Unit = {
		println(prespective(ClassLens.getClass))
	}

	@Test def testObject: Unit = {
		object Foo {
			def bar = v

			val v = "foo"
		}

		println(prespective(Foo.getClass))
		println(Foo.bar)
	}

	@Test def testCaseObject: Unit = {
		case object Foo {
			def bar = v

			val v = "foo"
		}

		println(prespective(Foo.getClass))
	}

	@Test def testCaseObjects: Unit = {
		sealed abstract class Fruits {

		}
		case object Apple extends Fruits
		case object Orange extends Fruits

		println(prespective(Apple.getClass))
		println(prespective(Orange.getClass))

		val a = Apple
		val o = Orange
		(a) match {
			case a => println(a)
			case o => println(o)
		}
	}
}