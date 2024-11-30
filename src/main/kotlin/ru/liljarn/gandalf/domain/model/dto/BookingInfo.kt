package ru.liljarn.gandalf.domain.model.dto

import java.time.LocalDate

data class BookingInfo(
    val book: Book,
    val dueDate: LocalDate,
)
