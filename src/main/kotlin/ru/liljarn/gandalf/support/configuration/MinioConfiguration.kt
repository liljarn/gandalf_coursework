package ru.liljarn.gandalf.support.configuration

import io.minio.BucketExistsArgs
import io.minio.MakeBucketArgs
import io.minio.MinioClient
import io.minio.SetBucketPolicyArgs
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.liljarn.gandalf.support.properties.MinioClientProperties

@Configuration
class MinioConfiguration(
    private val properties: MinioClientProperties,
) {

    @Bean
    fun minioClient() = MinioClient.builder()
        .endpoint(properties.url)
        .credentials(properties.accessKey, properties.secretKey)
        .build().apply {
            if (!bucketExists(BucketExistsArgs.builder().bucket(properties.bucketName).build())) {
                makeBucket(MakeBucketArgs.builder().bucket(properties.bucketName).build())
                setBucketPolicy(this)
            }
        }

    private fun setBucketPolicy(minioClient: MinioClient) {
        val policyJson = """
            {
                "Version": "2012-10-17",
                "Statement": [
                    {
                        "Effect": "Allow",
                        "Principal": "*",
                        "Action": "s3:GetObject",
                        "Resource": "arn:aws:s3:::${properties.bucketName}/*"
                    }
                ]
            }
        """.trimIndent()

        minioClient.setBucketPolicy(
            SetBucketPolicyArgs.builder()
                .bucket(properties.bucketName)
                .config(policyJson)
                .build()
        )
    }
}