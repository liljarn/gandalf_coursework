package ru.liljarn.gandalf.api.rest

import org.springframework.web.bind.annotation.*
import ru.liljarn.gandalf.api.model.response.UserResponse
import ru.liljarn.gandalf.domain.model.exception.UserUnauthorizedException
import ru.liljarn.gandalf.domain.service.JwtService
import ru.liljarn.gandalf.domain.service.user.UserService
import java.util.*

@RestController
@RequestMapping("/api/v1/user")
class UserDataController(
    private val userService: UserService, private val jwtService: JwtService
) {

    @GetMapping("/profile/self")
    fun getData(@RequestHeader("Authorization") token: String?): UserResponse =
        token?.replace("Bearer ", "")?.let { jwtToken ->
            UserResponse(userService.getUserData(jwtService.getUserId(jwtToken)), true)
        } ?: throw UserUnauthorizedException("User didn't provide personal token")

    @GetMapping("/profile/{uuid}")
    fun getUserProfileData(@RequestHeader("Authorization") token: String?, @PathVariable uuid: String): UserResponse =
        userService.getUserData(UUID.fromString(uuid)).let { data ->
            UserResponse(
                userData = data,
                self = token?.replace("Bearer ", "")?.let { jwtToken ->
                    jwtService.getUserId(jwtToken) == data.uuid
                } ?: false
            )
        }
}
