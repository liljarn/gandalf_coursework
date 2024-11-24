package ru.liljarn.gandalf.api.model.response

import java.time.LocalDate
import java.util.*

data class UserData(
    val uuid: UUID,
    val email: String,
    val firstName: String,
    val lastName: String,
    val birthDate: LocalDate,
    val photoUrl: String
)
