package ru.liljarn.gandalf.domain.service.notification

import org.springframework.stereotype.Service
import ru.liljarn.gandalf.api.model.request.MailingRequest
import ru.liljarn.gandalf.domain.model.dto.notification.AdminMailingNotification
import ru.liljarn.gandalf.domain.model.type.NotificationType
import ru.liljarn.gandalf.domain.service.user.component.UserDataComponent

@Service
class MailingService(
    private val userDataComponent: UserDataComponent,
    private val notificationService: NotificationService
) {

    fun sendMailing(mailingRequest: MailingRequest) {
        userDataComponent.getUserEmails().forEach { email ->
            notificationService.sendNotification(
                AdminMailingNotification(
                    email,
                    NotificationType.ADMIN_MAILING,
                    mailingRequest.title,
                    mailingRequest.payload
                )
            )
        }
    }
}
