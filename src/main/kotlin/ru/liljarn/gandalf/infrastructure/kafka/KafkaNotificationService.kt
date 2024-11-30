package ru.liljarn.gandalf.infrastructure.kafka

import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import ru.liljarn.gandalf.domain.model.dto.notification.Event
import ru.liljarn.gandalf.domain.service.notification.NotificationService

@Service
class KafkaNotificationService(
    private val kafkaTemplate: KafkaTemplate<String, Any>,
    @Value("\${kafka.topics.notification.destination}")
    private val topic: String
) : NotificationService {

    override fun sendNotification(notification: Event) {
        kafkaTemplate.send(topic, notification.email, notification)
    }
}
