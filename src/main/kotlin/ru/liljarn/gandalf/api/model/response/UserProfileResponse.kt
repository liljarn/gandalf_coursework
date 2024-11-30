package ru.liljarn.gandalf.api.model.response

import ru.liljarn.gandalf.domain.model.dto.BookingInfo
import ru.liljarn.gandalf.domain.model.dto.UserData

data class UserProfileResponse(
    val userData: UserData,
    val bookInfo: BookingInfo?
)
