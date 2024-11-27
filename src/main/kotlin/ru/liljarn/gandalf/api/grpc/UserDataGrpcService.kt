package ru.liljarn.gandalf.api.grpc

import net.devh.boot.grpc.server.service.GrpcService
import ru.liljarn.gandalf.domain.service.JwtService
import ru.liljarn.gandalf.domain.service.user.UserService
import ru.liljarn.gandalf.support.mapper.toTimestamp
import ru.liljarn.gandalf.support.mapper.toUuid
import ru.liljarn.gandalf.user.GetUserDataIdRequest
import ru.liljarn.gandalf.user.GetUserDataJwtRequest
import ru.liljarn.gandalf.user.UserDataServiceGrpcKt.UserDataServiceCoroutineImplBase
import ru.liljarn.gandalf.user.userDataResponse
import java.util.*

@GrpcService
class UserDataGrpcService(
    private val userService: UserService,
    private val jwtService: JwtService
) : UserDataServiceCoroutineImplBase() {

    override suspend fun getUserDataByJwt(request: GetUserDataJwtRequest) =
        getUserInfo(jwtService.getUserId(request.jwt))

    override suspend fun getUserDataById(request: GetUserDataIdRequest) =
        getUserInfo(request.uuid.toUuid())

    private fun getUserInfo(userId: UUID) =
        userService.getUserData(userId).let {
            userDataResponse {
                uuid = it.uuid.toString()
                email = it.email
                firstName = it.firstName
                lastName = it.lastName
                birthdate = it.birthDate.toTimestamp()
                photoUrl = it.photoUrl ?: ""
            }
        }
}
