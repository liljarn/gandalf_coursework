package ru.liljarn.gandalf.domain.model.dto

import ru.liljarn.gandalf.domain.model.type.BookStatus
import java.math.BigDecimal

data class Book(
    val bookId: Long,
    val bookName: String,
    val authorId: Long,
    val authorName: String,
    val authorPhotoUrl: String,
    val releaseYear: Short,
    val ageLimit: Short,
    val description: String,
    val photoUrl: String,
    val rating: BigDecimal,
    val status: BookStatus,
    val genres: ArrayList<Genre>
)
