package ru.liljarn.gandalf.support.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("api.management")
class ManagementApiProperties {
    lateinit var header: String
    lateinit var key: String
}
