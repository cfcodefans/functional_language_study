package org.apache.sqoop.model

import java.lang.reflect.Field
import java.security.{AccessController, PrivilegedAction}

import org.apache.commons.lang3.StringUtils
import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}
import org.apache.sqoop.common.SqoopException

/**
  * Util class for transforming data from correctly annotated configuration
  * objects to different structures and vice-versa.
  *
  * TODO: This class should see some overhaul into more reusable code, especially expose and re-use the methods at the end.
  */
@InterfaceAudience.Private
@InterfaceStability.Unstable
object ConfigUtils {
	private def getFieldFromName(klass: Class[_], name: String): Field = {
		var configField: Field = null
		if (StringUtils.isBlank(name))
			return configField

		try
			configField = klass.getDeclaredField(name)
		catch {
			case e: NoSuchFieldException => {
				// reverse lookup config field from custom config name
				val headOption: Option[Field] = klass.getDeclaredFields.filter(f => {
					val anno: Config = f.getAnnotation(classOf[Config])
					anno != null && name == anno.name
				}).headOption
				if (headOption.isEmpty)
					throw new SqoopException(ModelError.MODEL_006, "Missing field " + name + " on config class " + klass.getCanonicalName, e)
				return headOption.get
			}
		}
		configField
	}

	// The method setAccessible() requires a security permission check according to the FindBugs's rule (DP_DO_INSIDE_DO_PRIVILEGED)
	private def setFieldAccessibleWithAC(field: Field) {
		AccessController.doPrivileged(new PrivilegedAction[_]() {
			def run: Any = {
				field.setAccessible(true)
				null
			}
		})
	}

	def getFieldValue(field: Field, `object`: Any): Any = try
		setFieldAccessibleWithAC(field)
		field.get(`object`)
	catch {
		case e: IllegalAccessException => {
			throw new SqoopException(ModelError.MODEL_015, e)
		}
	}

	def getInputAnnotation(field: Field, strict: Boolean): Input = {
		val annotation: Input = field.getAnnotation(classOf[Input])
		if (strict && annotation == null) throw new SqoopException(ModelError.MODEL_003, "Missing annotation Input on Field " + field.getName + " on class " + field.getDeclaringClass.getName)
		annotation
	}
}
