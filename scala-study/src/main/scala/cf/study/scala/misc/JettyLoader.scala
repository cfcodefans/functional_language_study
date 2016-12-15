package cf.study.scala.misc

import java.nio.file.Paths

import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.logging.log4j.{LogManager, Logger}
import org.eclipse.jetty.annotations.AnnotationConfiguration
import org.eclipse.jetty.plus.webapp.{EnvConfiguration, PlusConfiguration}
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.webapp.Configuration.ClassList
import org.eclipse.jetty.webapp.{FragmentConfiguration, JettyWebXmlConfiguration, WebAppContext}

object JettyLoader {
	val log = LogManager.getLogger(classOf[JettyLoader])

	def main(args: Array[String]): Unit = {
		val jl = new JettyLoader(8086, "test", "D:\\git\\data-projects\\data.admin\\target\\data.admin-0.0.1")
		log.info(jl)
		
		jl.server.start
		jl.server.join
	}
}

class JettyLoader(val port: Int, val ctxPath: String, val warPath: String) {
	import JettyLoader._

	val server: Server = new Server()
	
	log.info(Server.getVersion)
	
	server.setAttribute("jetty.http.port", 9001)
	val webApp: WebAppContext = new WebAppContext
	
	log.info(classOf[WebAppContext].getProtectionDomain.getCodeSource.getLocation.toURI.getPath)

	{
		val clsList = ClassList.setServerDefault(server);
		clsList.addAfter(classOf[FragmentConfiguration].getName,
			classOf[EnvConfiguration].getName,
			classOf[PlusConfiguration].getName);

		clsList.addBefore(classOf[JettyWebXmlConfiguration].getName,
			classOf[AnnotationConfiguration].getName);

		webApp.setContextPath(ctxPath)
		val _warPath = Paths.get(warPath).toAbsolutePath.toString

		webApp.setWar(_warPath)

		server.setHandler(webApp)

		log.info("prepared: \n http://localhost:%d/%s".format(port, _warPath))
	}

	override def toString:String = ToStringBuilder.reflectionToString(this)
}