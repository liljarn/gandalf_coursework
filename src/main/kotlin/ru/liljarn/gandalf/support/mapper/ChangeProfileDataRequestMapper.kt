package ru.liljarn.gandalf.support.mapper

import ru.liljarn.gandalf.api.model.request.ChangeProfileDataRequest
import ru.liljarn.gandalf.domain.model.dto.ChangedProfileData

fun ChangeProfileDataRequest.toChangedProfileData(password: String?, salt: String?, photoUrl: String?) =
    ChangedProfileData(
        email = email,
        password = password,
        salt = salt,
        firstName = firstName,
        lastName = lastName,
        birthDate = birthDate,
        photoUrl = photoUrl
    )
