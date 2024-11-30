package ru.liljarn.gandalf.support.properties

import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "kafka")
class GandalfKafkaProperties {
    var clusters: Map<String, KafkaProperties> = mutableMapOf()
}
