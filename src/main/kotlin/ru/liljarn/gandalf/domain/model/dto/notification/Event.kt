package ru.liljarn.gandalf.domain.model.dto.notification

import ru.liljarn.gandalf.domain.model.type.NotificationType

interface Event {
    val email: String
    val eventType: NotificationType
}
