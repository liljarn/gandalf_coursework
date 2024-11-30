package ru.liljarn.gandalf.api.rest

import org.springframework.web.bind.annotation.*
import ru.liljarn.gandalf.api.model.request.ChangeProfileDataRequest
import ru.liljarn.gandalf.api.model.response.UserProfileResponse
import ru.liljarn.gandalf.domain.service.JwtService
import ru.liljarn.gandalf.domain.service.user.UserService
import java.util.*

@RestController
@RequestMapping("/api/v1/user")
class UserDataController(
    private val userService: UserService,
    private val jwtService: JwtService
) {

    @GetMapping("/profile/self")
    fun getData(@RequestHeader("Authorization") token: String): UserProfileResponse =
        token.replace("Bearer ", "").let { jwtToken ->
            userService.getUserProfileInfo(jwtService.getUserId(jwtToken))
        }

    @GetMapping("/profile/{uuid}")
    fun getUserProfileData(@PathVariable uuid: String): UserProfileResponse =
        userService.getUserProfileInfo(UUID.fromString(uuid))

    @PutMapping("/profile/self")
    fun editProfile(@RequestHeader("Authorization") token: String, @ModelAttribute req: ChangeProfileDataRequest) {
        token.replace("Bearer ", "").let { jwtToken ->
            userService.editProfile(jwtService.getUserId(jwtToken), req)
        }
    }
}
