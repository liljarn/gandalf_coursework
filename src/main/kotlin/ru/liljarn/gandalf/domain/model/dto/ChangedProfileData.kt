package ru.liljarn.gandalf.domain.model.dto

import java.time.LocalDate

data class ChangedProfileData(
    val email: String?,
    val password: String?,
    val salt: String?,
    val firstName: String?,
    val lastName: String?,
    val birthDate: LocalDate?,
    val photoUrl: String?
)