package ru.liljarn.gandalf.domain.service.user.component

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.liljarn.gandalf.domain.model.dto.ChangedProfileData
import ru.liljarn.gandalf.domain.model.dto.UserData
import ru.liljarn.gandalf.domain.model.dto.UserPrivateData
import ru.liljarn.gandalf.domain.model.entity.UserDataEntity
import ru.liljarn.gandalf.domain.model.exception.UserNotFoundException
import ru.liljarn.gandalf.domain.repository.UserDataRepository
import ru.liljarn.gandalf.support.mapper.toUserData
import ru.liljarn.gandalf.support.mapper.toUserPrivateData
import java.util.*

@Component
class UserDataComponent(
    private val userDataRepository: UserDataRepository
) {

    @Transactional
    fun createUserData(userData: UserPrivateData) =
        userDataRepository.addUser(
            UserDataEntity(
                uuid = userData.uuid,
                email = userData.email,
                password = userData.password,
                salt = userData.salt,
                firstName = userData.firstName,
                lastName = userData.lastName,
                birthDate = userData.birthDate,
                photoUrl = userData.photoUrl
            )
        )

    @Transactional(readOnly = true)
    fun findUserDataByEmail(email: String): Boolean = userDataRepository.existsByEmail(email)

    @Transactional
    fun getUserData(userUuid: UUID): UserData {
        val userDataEntity =
            userDataRepository.findByUuid(userUuid) ?: throw UserNotFoundException("User with userId=$userUuid not found")
        return userDataEntity.toUserData()
    }

    @Transactional(readOnly = true)
    fun getUserPrivateData(email: String): UserPrivateData =
        userDataRepository.findByEmail(email)?.toUserPrivateData()
            ?: throw UserNotFoundException("User with email=$email not found")

    @Transactional
    fun editProfile(userUuid: UUID, data: ChangedProfileData) {
        val user = userDataRepository.findByUuid(userUuid)
            ?: throw UserNotFoundException("User with userId=$userUuid not found")

        userDataRepository.save(
            UserDataEntity(
                uuid = user.uuid,
                email = data.email ?: user.email,
                salt = data.salt ?: user.salt,
                password = data.password ?: user.password,
                firstName = data.firstName ?: user.firstName,
                lastName = data.lastName ?: user.lastName,
                birthDate = data.birthDate ?: user.birthDate,
                photoUrl = data.photoUrl ?: user.photoUrl
            )
        )
    }

    @Transactional(readOnly = true)
    fun getUserEmails() = userDataRepository.getUserEmails()
}
