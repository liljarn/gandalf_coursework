package ru.liljarn.gandalf.support.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import ru.liljarn.gandalf.support.properties.WebClientProperties


@Configuration
class WebClientConfiguration(
    private val props: WebClientProperties
) {
    @Bean
    fun webClientBuilders(): WebClientBuilders {
        val webClientBuilders = emptyMap<String, WebClient.Builder>().toMutableMap()
        for ((key, value) in props.clients) {
            val webClientBuilder = WebClient.builder().baseUrl(value.destination)
            for ((headerKey, headerValue) in value.headers) {
                webClientBuilder.defaultHeader(headerKey, headerValue)
            }
            webClientBuilders[key] = webClientBuilder
        }
        return WebClientBuilders(webClientBuilders)
    }
}

data class WebClientBuilders(
    val builders: Map<String, WebClient.Builder>
)
