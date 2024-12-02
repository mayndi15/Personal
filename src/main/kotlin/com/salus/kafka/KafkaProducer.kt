package com.salus.com.salus.kafka

import kafka.producer.KafkaProducers

object KafkaProducer {

    private val producer = KafkaProducers<String, Any>("localhost:9092")

    fun publish(message: Any) {
        try {
            producer.send("topic-auth-api", "key", message)
        } catch (e: Exception) {
            println("Error sending message: ${e.message}")
        } finally {
            //producer.close()
        }
    }
}


