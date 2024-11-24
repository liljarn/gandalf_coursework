package ru.liljarn.gandalf.domain.repository

import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import ru.liljarn.gandalf.domain.model.entity.UserDataEntity
import java.util.UUID

@Repository
interface UserDataRepository : CrudRepository<UserDataEntity, UUID> {
    fun existsByEmail(email: String): Boolean

    fun findByEmail(email: String): UserDataEntity?

    fun findByUuid(uuid: UUID): UserDataEntity?

    @Modifying
    @Query("""
        INSERT INTO user_data (uuid, email, password, salt, first_name, last_name, birth_date, photo_url)
        VALUES (:#{#data.uuid}, :#{#data.email}, :#{#data.password}, :#{#data.salt}, :#{#data.firstName}, :#{#data.lastName}, :#{#data.birthDate}, :#{#data.photoUrl})
        """)
    fun save(@Param("data") userData: UserDataEntity)
}