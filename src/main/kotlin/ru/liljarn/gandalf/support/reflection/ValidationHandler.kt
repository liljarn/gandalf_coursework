package ru.liljarn.gandalf.support.reflection

import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Component
import ru.liljarn.gandalf.support.properties.ManagementApiProperties

@Component
class ValidationHandler(
    private val managementApiProperties: ManagementApiProperties,
) {

    fun validateHeader(request: HttpServletRequest): Boolean {
        val managementRequestKey = request.getHeader(managementApiProperties.header)
        return managementRequestKey == managementApiProperties.key
    }
}
