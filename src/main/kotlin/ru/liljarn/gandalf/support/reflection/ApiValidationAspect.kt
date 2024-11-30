package ru.liljarn.gandalf.support.reflection

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import ru.liljarn.gandalf.domain.model.exception.WrongPasswordException

@Aspect
@Component
class ApiValidationAspect(
    private val validationHandler: ValidationHandler
) {

    @Pointcut("within(@ru.liljarn.gandalf.support.reflection.ManagementApi *)")
    fun classWithManagementApiAnnotation() {
    }

    @Before("classWithManagementApiAnnotation()")
    fun checkHeaderBeforeMethod(joinPoint: JoinPoint) {
        val request = (RequestContextHolder.currentRequestAttributes() as? ServletRequestAttributes)?.request

        if (request != null && !validationHandler.validateHeader(request)) {
            throw WrongPasswordException("Wrong token")
        }
    }
}
