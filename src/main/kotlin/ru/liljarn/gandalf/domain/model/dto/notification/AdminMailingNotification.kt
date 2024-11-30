package ru.liljarn.gandalf.domain.model.dto.notification

import ru.liljarn.gandalf.domain.model.type.NotificationType

data class AdminMailingNotification(
    override val email: String,
    override val eventType: NotificationType,
    val title: String,
    val payload: String
) : Event
