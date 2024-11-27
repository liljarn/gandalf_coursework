package ru.liljarn.gandalf.domain.service.user

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service
import ru.liljarn.gandalf.domain.model.exception.UserAlreadyExistsException
import ru.liljarn.gandalf.domain.model.exception.WrongPasswordException
import ru.liljarn.gandalf.api.model.request.AuthenticationRequest
import ru.liljarn.gandalf.api.model.request.RegistrationRequest
import ru.liljarn.gandalf.domain.model.dto.UserData
import ru.liljarn.gandalf.domain.service.encryption.EncryptionService
import ru.liljarn.gandalf.domain.service.image.ImageService
import ru.liljarn.gandalf.domain.service.user.component.UserDataComponent
import ru.liljarn.gandalf.support.mapper.toUserPrivateData
import java.util.*

private val logger = KotlinLogging.logger {}

@Service
class UserService(
    private val userDataComponent: UserDataComponent,
    private val encryptionService: EncryptionService,
    private val imageService: ImageService
) {

    fun createUser(request: RegistrationRequest): UUID {
        logger.info { "Start creating user, email=${request.email}" }
        logger.info { request.profileImage?.inputStream }
        val uuid = UUID.randomUUID()
        val photoUrl = request.profileImage?.let {
            imageService.uploadImage(request.profileImage.inputStream, uuid.toString())
        }
        val passwordSalt = encryptionService.generateSalt()
        val hashedPassword = encryptionService.encrypt(request.password, passwordSalt)

        if (userExistsByEmail(request.email)) {
            throw UserAlreadyExistsException("User with email ${request.email} already exists")
        }

        request.toUserPrivateData(uuid, hashedPassword, passwordSalt, photoUrl).let {
            userDataComponent.createUserData(it)
        }

        logger.info { "End of creating user, email=${request.email}" }
        return uuid
    }

    fun verifyUser(request: AuthenticationRequest): UUID {
        logger.info { "Start verifying user, email=${request.email}" }

        val userPrivateData =  userDataComponent.getUserPrivateData(request.email)
        val hashedPassword = encryptionService.encrypt(request.password, userPrivateData.salt)
        if (hashedPassword != userPrivateData.password) {
            throw WrongPasswordException("Password is incorrect")
        }

        logger.info { "End of verifying user, email=${request.email}" }
        return userPrivateData.uuid
    }

    fun userExistsByEmail(email: String): Boolean = userDataComponent.findUserDataByEmail(email)

    fun getUserData(userUuid: UUID): UserData = userDataComponent.getUserData(userUuid)
}
