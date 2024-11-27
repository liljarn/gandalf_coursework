package ru.liljarn.gandalf.api.model.response

import ru.liljarn.gandalf.domain.model.dto.UserData

data class UserResponse(
    val userData: UserData,
    val self: Boolean
)
