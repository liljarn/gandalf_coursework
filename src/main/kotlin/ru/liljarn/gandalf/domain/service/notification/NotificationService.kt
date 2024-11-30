package ru.liljarn.gandalf.domain.service.notification

import ru.liljarn.gandalf.domain.model.dto.notification.Event

interface NotificationService {
    fun sendNotification(notification: Event)
}
