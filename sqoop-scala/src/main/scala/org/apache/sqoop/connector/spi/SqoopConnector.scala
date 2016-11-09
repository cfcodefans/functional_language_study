package org.apache.sqoop.connector.spi

import java.util.{Locale, ResourceBundle}

import org.apache.sqoop.classification.{InterfaceAudience, InterfaceStability}
import org.apache.sqoop.common.Direction
import org.apache.sqoop.common.Direction.Direction

/**
  * Service provider interface for Sqoop Connectors.
  */
@InterfaceAudience.Public
@InterfaceStability.Evolving abstract class SqoopConnector {
	private var connectorName: String = null

	def setConnectorName(connectorName: String) {
		this.connectorName = connectorName
	}

	def getConnectorName: String = this.connectorName

	/**
	  * Retrieve connector version.
	  *
	  * @return Version encoded as a string
	  */
	def getVersion: String

	/**
	  * @param locale
	  * @return the resource bundle associated with the given locale.
	  */
	def getBundle(locale: Locale): ResourceBundle

	/**
	  * @return The supported directions
	  */
	def getSupportedDirections: List[Direction] = Array[Direction](Direction.FROM, Direction.TO).toList

	/**
	  * @return Get link configuration group class
	  */
	@SuppressWarnings(Array("rawtypes")) def getLinkConfigurationClass: Class[_]

	/**
	  * @return Get job configuration group class per direction type or null if not supported
	  */
	@SuppressWarnings(Array("rawtypes")) def getJobConfigurationClass(direction: Direction): Class[_]

	/**
	  * @return an <tt>From</tt> that provides classes for performing import.
	  */
	def getFrom: From

	/**
	  * @return an <tt>To</tt> that provides classes for performing export.n
	  */
	def getTo: To

	/**
	  * Returns an {@linkplain ConnectorConfigurableUpgrader} object that can upgrade the
	  * configs related to the link and job
	  *
	  * @return ConnectorConfigurableUpgrader object
	  */
	def getConfigurableUpgrader(oldConnectorVersion: String): ConnectorConfigurableUpgrader

	/**
	  * Returns the {@linkplain IntermediateDataFormat} this connector
	  * can return natively in. This will support retrieving the data as text
	  * and an array of objects. This should never return null.
	  *
	  * @return { @linkplain IntermediateDataFormat} object
	  */
	def getIntermediateDataFormat: Class[_ <: IntermediateDataFormat[_]] = classOf[CSVIntermediateDataFormat]
}

