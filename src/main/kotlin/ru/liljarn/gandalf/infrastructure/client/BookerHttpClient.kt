package ru.liljarn.gandalf.infrastructure.client

import org.springframework.stereotype.Component
import ru.liljarn.gandalf.domain.client.BookerClient
import ru.liljarn.gandalf.domain.model.dto.BookingInfo
import ru.liljarn.gandalf.support.factory.WebClientFactory
import java.util.*

@Component
class BookerHttpClient(
    webClientFactory: WebClientFactory
) : BookerClient {

    val webClient by lazy {
        webClientFactory.createWebClient("booker")
    }

    override fun getBookingInfo(userId: UUID) = webClient.get()
        .uri("/api/v1/internal/booking/{userId}", userId)
        .retrieve()
        .bodyToMono(BookingInfo::class.java)
        .block()
}
