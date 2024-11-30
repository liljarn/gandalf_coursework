package ru.liljarn.gandalf.domain.client

import ru.liljarn.gandalf.domain.model.dto.BookingInfo
import java.util.*

interface BookerClient {
    fun getBookingInfo(userId: UUID): BookingInfo?
}
