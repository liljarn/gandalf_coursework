package ru.liljarn.gandalf.domain.service.user.component

import org.springframework.stereotype.Component
import ru.liljarn.gandalf.domain.model.exception.UserNotFoundException
import ru.liljarn.gandalf.domain.model.dto.UserPrivateData
import ru.liljarn.gandalf.domain.model.entity.UserDataEntity
import ru.liljarn.gandalf.domain.model.dto.UserData
import ru.liljarn.gandalf.domain.repository.UserDataRepository
import ru.liljarn.gandalf.support.mapper.toUserData
import ru.liljarn.gandalf.support.mapper.toUserPrivateData
import java.util.UUID

@Component
class UserDataComponent(
    private val userDataRepository: UserDataRepository
) {

    fun createUserData(userData: UserPrivateData) =
        userDataRepository.save(
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

    fun findUserDataByEmail(email: String): Boolean = userDataRepository.existsByEmail(email)

    fun getUserData(userUuid: UUID): UserData {
        val userDataEntity =
            userDataRepository.findByUuid(userUuid) ?: throw UserNotFoundException("User with uuid=$userUuid not found")
        return userDataEntity.toUserData()
    }

    fun getUserPrivateData(email: String): UserPrivateData =
        userDataRepository.findByEmail(email)?.toUserPrivateData()
            ?: throw UserNotFoundException("User with email=$email not found")
}
