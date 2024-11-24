package ru.liljarn.gandalf.api.rest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.liljarn.gandalf.api.model.response.UserData
import ru.liljarn.gandalf.domain.service.JwtService
import ru.liljarn.gandalf.domain.service.user.UserService
import java.util.UUID

@RestController
@RequestMapping("/api/v1/user")
class UserDataController(
    private val userService: UserService,
    private val jwtService: JwtService
) {

    @GetMapping("/profile/self")
    fun getData(@RequestHeader("Authorization") token: String): UserData =
        token.replace("Bearer ", "").let {
            jwtService.getUserId(it).let {
                userService.getUserData(it)
            }
        }

    @GetMapping("/profile/{uuid}")
    fun getUserProfileData(@PathVariable uuid: String): UserData =
        userService.getUserData(UUID.fromString(uuid))
}
