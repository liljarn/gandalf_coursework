package ru.liljarn.gandalf.api.model.request

data class MailingRequest(
    val title: String,
    val payload: String,
)
