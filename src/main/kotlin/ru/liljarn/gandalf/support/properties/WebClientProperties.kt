package ru.liljarn.gandalf.support.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "web-clients")
class WebClientProperties {
    var clients: Map<String, ClientProperties> = emptyMap()

    class ClientProperties {
        var destination: String = ""
        var headers: Map<String, String> = emptyMap()
    }
}
