package ru.liljarn.gandalf.support.mapper

import ru.liljarn.gandalf.domain.model.dto.UserPrivateData
import ru.liljarn.gandalf.api.model.response.UserData
import ru.liljarn.gandalf.domain.model.entity.UserDataEntity

fun UserDataEntity.toUserData() = UserData(
    uuid = uuid,
    email = email,
    firstName = firstName,
    lastName = lastName,
    birthDate = birthDate,
    photoUrl = photoUrl
)

fun UserDataEntity.toUserPrivateData() = UserPrivateData(
    uuid = uuid,
    email = email,
    password = password,
    salt = salt,
    firstName = firstName,
    lastName = lastName,
    birthDate = birthDate,
    photoUrl = photoUrl
)