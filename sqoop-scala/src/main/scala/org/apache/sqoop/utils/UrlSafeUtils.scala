package org.apache.sqoop.utils

import java.io.UnsupportedEncodingException
import java.net.{URLDecoder, URLEncoder}

import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}

/**
  * Miscellaneous utility methods that help in URL-safe communication over HTTP.
  */
@InterfaceAudience.Private
@InterfaceStability.Unstable
object UrlSafeUtils {
	val ENCODING_UTF8: String = "UTF-8"

	def urlEncode(string: String): String = try
		URLEncoder.encode(string, ENCODING_UTF8)

	catch {
		case uee: UnsupportedEncodingException => {
			throw new RuntimeException(uee)
		}
	}

	def urlDecode(string: String): String = try
		URLDecoder.decode(string, ENCODING_UTF8)

	catch {
		case uee: UnsupportedEncodingException => {
			throw new RuntimeException(uee)
		}
	}

	def urlPathEncode(path: String): String = try
		URLEncoder.encode(URLEncoder.encode(path, ENCODING_UTF8), ENCODING_UTF8)

	catch {
		case uee: UnsupportedEncodingException => {
			throw new RuntimeException(uee)
		}
	}

	def urlPathDecode(path: String): String = try
		URLDecoder.decode(URLDecoder.decode(path, ENCODING_UTF8), ENCODING_UTF8)
	catch {
		case uee: UnsupportedEncodingException => {
			throw new RuntimeException(uee)
		}
	}
}
