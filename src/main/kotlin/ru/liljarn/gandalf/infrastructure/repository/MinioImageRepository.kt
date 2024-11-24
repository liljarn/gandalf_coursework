package ru.liljarn.gandalf.infrastructure.repository

import io.minio.MinioClient
import io.minio.PutObjectArgs
import org.springframework.stereotype.Component
import ru.liljarn.gandalf.domain.repository.ImageRepository
import ru.liljarn.gandalf.support.properties.MinioClientProperties
import java.io.InputStream

@Component
class MinioImageRepository(
    private val properties: MinioClientProperties,
    private val minioClient: MinioClient
) : ImageRepository {

    override fun uploadImage(file: InputStream, name: String) =
        minioClient.putObject(
            PutObjectArgs.builder()
                .bucket(properties.bucketName)
                .`object`(name)
                .stream(file, -1, 10485760)
                .contentType("image/jpeg")
                .build()
        ).let {
            getImageUrl(name)
        }


    override fun getImageUrl(name: String) = "${properties.url}/${properties.bucketName}/$name"
}
