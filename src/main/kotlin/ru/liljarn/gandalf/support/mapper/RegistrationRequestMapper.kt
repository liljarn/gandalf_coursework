package ru.liljarn.gandalf.support.mapper

import ru.liljarn.gandalf.domain.model.dto.UserPrivateData
import ru.liljarn.gandalf.api.model.request.RegistrationRequest
import java.util.*

fun RegistrationRequest.toUserPrivateData(uuid: UUID, password: String, salt: String, photoUrl: String?) =
    UserPrivateData(
        uuid = uuid,
        email = email,
        password = password,
        salt = salt,
        firstName = firstName,
        lastName = lastName,
        birthDate = birthDate,
        photoUrl = photoUrl
    )