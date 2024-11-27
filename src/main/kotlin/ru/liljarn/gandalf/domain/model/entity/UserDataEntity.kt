package ru.liljarn.gandalf.domain.model.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate
import java.util.*

@Table("user_data")
data class UserDataEntity(
    @Id
    val uuid: UUID,
    val email: String,
    val password: String,
    val salt: String,
    val firstName: String,
    val lastName: String,
    val birthDate: LocalDate,
    val photoUrl: String?
)
