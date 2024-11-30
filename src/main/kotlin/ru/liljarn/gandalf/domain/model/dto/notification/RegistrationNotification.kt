package ru.liljarn.gandalf.domain.model.dto.notification

import ru.liljarn.gandalf.domain.model.type.NotificationType

data class RegistrationNotification(
    override val email: String,
    override val eventType: NotificationType,
    val firstName: String
) : Event
