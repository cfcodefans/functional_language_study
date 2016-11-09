package org.apache.sqoop.connector.kafka

import org.apache.sqoop.job.Constants

/**
  * Created by fan on 2016/8/25.
  */
object KafkaConstants extends Constants {
	// Resource bundle name
	val RESOURCE_BUNDLE_NAME: String = "kafka-connector-config"
	// Kafka properties keys
	val MESSAGE_SERIALIZER_KEY: String = "serializer.class"
	val KEY_SERIALIZER_KEY: String = "key.serializer.class"
	val BROKER_LIST_KEY: String = "metadata.broker.list"
	val REQUIRED_ACKS_KEY: String = "request.required.acks"
	val PRODUCER_TYPE: String = "producer.type"
	// Kafka properties default values
	val DEFAULT_MESSAGE_SERIALIZER: String = "kafka.serializer.StringEncoder"
	val DEFAULT_KEY_SERIALIZER: String = "kafka.serializer.StringEncoder"
	val DEFAULT_REQUIRED_ACKS: String = "-1"
	val DEFAULT_PRODUCER_TYPE: String = "sync"
	val DEFAULT_BATCH_SIZE: Int = 100
}
