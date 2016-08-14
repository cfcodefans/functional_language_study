package org.apache.sqoop.utils

import java._
import java.lang.ref.WeakReference
import java.util.Collections

import org.apache.commons.lang3.StringUtils
import org.apache.logging.log4j.{LogManager, Logger}

import scala.collection.mutable.WeakHashMap

/**
  * Created by fan on 2016/8/14.
  */
object ClassUtils {
	private[ClassUtils] val LOG: Logger = LogManager.getLogger(ClassUtils.getClass)
	private val CACHE_CLASSES: WeakHashMap[ClassLoader, util.Map[String, WeakReference[Class[_]]]] = WeakHashMap.empty[ClassLoader, util.Map[String, WeakReference[Class[_]]]]

	/**
	  * A unique class which is used as a sentinel value in the caching
	  * for loadClass.
	  */
	abstract private class NegativeCacheSentinel {}

	/**
	  * Sentinel value to store negative cache results in {@link #CACHE_CLASSES}.
	  */
	private val NEGATIVE_CACHE_SENTINEL: Class[_] = classOf[ClassUtils.NegativeCacheSentinel]

	/**
	  * Load class by given name and classLoader.
	  *
	  * This method will return null in case that the class is not found, no
	  * exception will be rised.
	  *
	  * @param className Name of class
	  * @param loader    classLoader to load the given class
	  * @return Class instance or NULL
	  */
	def loadClassWithClassLoader(className: String, loader: ClassLoader): Class[_] = {
		if (StringUtils.isBlank(className)) return null

		var map: util.Map[String, WeakReference[Class[_]]] = null
		CACHE_CLASSES.synchronized({
			map = CACHE_CLASSES.getOrElseUpdate(loader, {
				Collections.synchronizedMap(new util.WeakHashMap[String, WeakReference[Class[_]]])
			})
		})

		var klass: Class[_] = null
		val ref: WeakReference[Class[_]] = map.get(className)
		if (ref != null) klass = ref.get

		if (klass == null) {
			try {
				klass = Class.forName(className, true, loader)
			} catch {
				case ex:ClassNotFoundException => {
					// Leave a marker that the class isn't found
					map.put(className, new WeakReference[Class[_]](NEGATIVE_CACHE_SENTINEL))
					LOG.debug("Exception while loading class: " + className, ex)
					return null
				}
			}
			// two putters can race here, but they'll put the same class
			map.put(className, new WeakReference[Class[_]](klass))
			return klass
		}

		if (klass eq NEGATIVE_CACHE_SENTINEL) return null // not found

		return klass
	}

	def getClassLoader: ClassLoader = {
		var classLoader: ClassLoader = Thread.currentThread.getContextClassLoader
		if (classLoader == null) classLoader = ClassUtils.getClassLoader
		classLoader
	}

	/**
	  * Load class by given name and return corresponding Class object.
	  *
	  * This method will return null in case that the class is not found, no
	  * exception will be rised.
	  *
	  * @param className Name of class
	  * @return Class instance or NULL
	  */
	def loadClass(className: String): Class[_] = loadClassWithClassLoader(className, getClassLoader)
}
