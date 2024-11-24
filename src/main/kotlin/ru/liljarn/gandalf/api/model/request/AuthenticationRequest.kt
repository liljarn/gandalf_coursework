package ru.liljarn.gandalf.api.model.request

data class AuthenticationRequest(
    val email: String,
    val password: String
)
