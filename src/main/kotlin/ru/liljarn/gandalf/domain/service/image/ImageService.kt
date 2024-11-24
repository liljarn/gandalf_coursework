package ru.liljarn.gandalf.domain.service.image

import org.springframework.stereotype.Service
import ru.liljarn.gandalf.domain.repository.ImageRepository
import java.io.InputStream

@Service
class ImageService(
    private val imageRepository: ImageRepository,
) {
    fun uploadImage(file: InputStream, name: String): String = imageRepository.uploadImage(file, name)
}