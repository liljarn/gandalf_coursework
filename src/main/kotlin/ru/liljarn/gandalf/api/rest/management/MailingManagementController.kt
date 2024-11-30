package ru.liljarn.gandalf.api.rest.management

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.liljarn.gandalf.api.model.request.MailingRequest
import ru.liljarn.gandalf.domain.service.notification.MailingService
import ru.liljarn.gandalf.support.reflection.ManagementApi

@ManagementApi
@RestController
@RequestMapping("/api/v1/management/mailing")
class MailingManagementController(
    private val mailingService: MailingService,
) {

    @PostMapping
    fun sendMailingToAllUsers(@RequestBody mailingRequest: MailingRequest) =
        mailingService.sendMailing(mailingRequest)
}
