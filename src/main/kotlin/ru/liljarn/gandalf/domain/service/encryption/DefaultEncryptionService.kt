package ru.liljarn.gandalf.domain.service.encryption

import org.mindrot.jbcrypt.BCrypt
import org.springframework.stereotype.Service


@Service
class DefaultEncryptionService : EncryptionService {
    override fun encrypt(payload: String, salt: String): String = BCrypt.hashpw(payload, salt)

    override fun generateSalt(): String = BCrypt.gensalt()

    override fun isCorrect(payload: String, salt: String) = BCrypt.checkpw(payload, encrypt(payload, salt))
}