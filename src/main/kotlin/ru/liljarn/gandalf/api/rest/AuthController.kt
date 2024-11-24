package ru.liljarn.gandalf.api.rest

import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.liljarn.gandalf.api.model.request.AuthenticationRequest
import ru.liljarn.gandalf.api.model.request.RegistrationRequest
import ru.liljarn.gandalf.api.model.response.TokenResposne
import ru.liljarn.gandalf.domain.service.JwtService
import ru.liljarn.gandalf.domain.service.user.UserService

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    private val jwtService: JwtService,
    private val userService: UserService
) {

    @PostMapping("/signin")
    fun signin(@RequestBody authenticationRequest: AuthenticationRequest): TokenResposne {
        val userUuid = userService.verifyUser(authenticationRequest)
        val token = jwtService.generate(userUuid)
        return TokenResposne(token)
    }

    @PostMapping("/signup")
    fun signup(@ModelAttribute registrationRequest: RegistrationRequest): TokenResposne {
        val userUuid = userService.createUser(registrationRequest)
        val token = jwtService.generate(userUuid)
        return TokenResposne(token)
    }
}