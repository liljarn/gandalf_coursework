package ru.liljarn.gandalf

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import ru.liljarn.gandalf.support.properties.GandalfKafkaProperties
import ru.liljarn.gandalf.support.properties.ManagementApiProperties
import ru.liljarn.gandalf.support.properties.WebClientProperties

@SpringBootApplication
@EnableConfigurationProperties(
    WebClientProperties::class,
    GandalfKafkaProperties::class,
    ManagementApiProperties::class
)
class GandalfApplication

fun main(args: Array<String>) {
    runApplication<GandalfApplication>(*args)
}
