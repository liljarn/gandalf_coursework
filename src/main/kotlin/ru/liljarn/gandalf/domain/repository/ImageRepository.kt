package ru.liljarn.gandalf.domain.repository

import java.io.InputStream

interface ImageRepository {
    fun uploadImage(file: InputStream, name: String): String

    fun getImageUrl(name: String): String
}
